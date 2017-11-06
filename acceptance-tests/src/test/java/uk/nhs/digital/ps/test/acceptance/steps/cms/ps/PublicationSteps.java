package uk.nhs.digital.ps.test.acceptance.steps.cms.ps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import uk.nhs.digital.ps.test.acceptance.config.AcceptanceTestProperties;
import uk.nhs.digital.ps.test.acceptance.data.TestDataFactory;
import uk.nhs.digital.ps.test.acceptance.data.TestDataRepo;
import uk.nhs.digital.ps.test.acceptance.models.Attachment;
import uk.nhs.digital.ps.test.acceptance.models.FileType;
import uk.nhs.digital.ps.test.acceptance.models.Publication;
import uk.nhs.digital.ps.test.acceptance.pages.ContentPage;
import uk.nhs.digital.ps.test.acceptance.pages.site.ps.AttachmentElement;
import uk.nhs.digital.ps.test.acceptance.pages.site.ps.PublicationPage;
import uk.nhs.digital.ps.test.acceptance.pages.site.ps.PublicationSeriesPage;
import uk.nhs.digital.ps.test.acceptance.steps.AbstractSpringSteps;
import uk.nhs.digital.ps.test.acceptance.steps.cms.LoginSteps;
import uk.nhs.digital.ps.test.acceptance.util.FileHelper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.slf4j.LoggerFactory.getLogger;
import static uk.nhs.digital.ps.test.acceptance.util.AssertionHelper.assertWithinTimeoutThat;
import static uk.nhs.digital.ps.test.acceptance.util.FileHelper.readFileAsByteArray;
import static uk.nhs.digital.ps.test.acceptance.util.FileHelper.waitUntilFileAppears;

public class PublicationSteps extends AbstractSpringSteps {

    private final static Logger log = getLogger(PublicationSteps.class);

    @Autowired
    private TestDataRepo testDataRepo;

    @Autowired
    private AcceptanceTestProperties acceptanceTestProperties;

    @Autowired
    private ContentPage contentPage;

    @Autowired
    private LoginSteps loginSteps;

    @Autowired
    private PublicationPage publicationPage;

    @Autowired
    private PublicationSeriesPage publicationSeriesPage;

    // Scenario: New Publication screen ========================================================================
    @Given("^I am on the content page$")
    public void givenIAmOnContentPage() throws Throwable {
        loginSteps.givenIAmLoggedInAsAdmin();
        assertThat("Current page should be content page.", contentPage.openContentTab(), is(true));
    }

    @When("^I create a new publication$")
    public void whenICreateANewPublication() throws Throwable {
        final Publication publication = TestDataFactory.createValidPublication().build();
        testDataRepo.setCurrentPublication(publication);

        assertThat("New publication created.", contentPage.newPublication(publication), is(true));
    }

    @Then("^an edit screen is displayed which allows me to populate details of the publication")
    public void thenAnEditScreenIsDisplayed() throws Throwable {
        assertThat("Publication edit screen is displayed",contentPage.isPublicationEditScreenOpen(),
            is(true));
    }

    // Scenario: Saving a publication ========================================================================
    @Given("^I am on the edit screen")
    public void givenIamOnTheEditScreen() throws Throwable {
        final Publication publication = TestDataFactory.createValidPublication().build();

        testDataRepo.setCurrentPublication(publication);

        loginSteps.givenIAmLoggedInAsAdmin();
        contentPage.openContentTab();
        contentPage.newPublication(publication);

        // Since previous step created a new document which was not saved, immediately after a login,
        // the edit document screen is displayed (instead of dashboard)
        assertThat("Publication edit screen is displayed",contentPage.isPublicationEditScreenOpen(),
            is(true));
    }

    @When("^I populate and save the publication")
    public void whenIPopulateAndSaveThePublication() throws Throwable {

        contentPage.populatePublication(testDataRepo.getCurrentPublication());
        contentPage.saveAndClosePublication();
    }

    @Then(("^it is saved"))
    public void thenItIsSaved() throws Throwable {
        assertThat("Publication is saved",contentPage.isPublicationSaved(), is(true));
    }


    // Scenario: Publishing a publication ========================================================================
    @Given("^I have saved a publication")
    public void givenIHaveSavedAPublication() throws Throwable {
        loginSteps.givenIAmLoggedInAsAdmin();
        contentPage.openContentTab();
        contentPage.navigateToDocument(testDataRepo.getCurrentPublication().getName());
    }

    @When("^I publish the publication")
    public void whenIPublishThePublication() throws Throwable {
        contentPage.publish();
        Thread.sleep(1000); // wait for doc to publish

    }

    // Scenario: Forbidden file type upload rejection ==================================================================
    @When("^I try to upload a file of one of the forbidden types:$")
    public void iTryToUploadAFileOfOneOfTheForbiddenTypes(final DataTable forbiddenExtensions) throws Throwable {

        final List<Attachment> forbiddenAttachments = forbiddenExtensions.asList(String.class).stream()
            .map(extension -> FileType.valueOf(extension.toUpperCase()))
            .map(forbiddenFileType -> TestDataFactory.createAttachmentOfType(forbiddenFileType).build())
            .collect(toList());

        testDataRepo.setCurrentAttachments(forbiddenAttachments);
    }

    @Then("^the upload is rejected with an error message$")
    public void theUploadIsRejectedWithAnErrorMessage() throws Throwable {

        contentPage.getAttachmentsSection().addUploadField();

        testDataRepo.getCurrentAttachments().forEach(attachment -> {
            contentPage.getAttachmentsSection().uploadAttachment(attachment);

            final String expectedErrorMessage = MessageFormat.format(
                "The uploaded file ''{0}'' has extension {1} which is not allowed.",
                attachment.getFullName(),
                attachment.getFileType().getExtension()
            );

            assertWithinTimeoutThat("Error message is displayed",
               () -> contentPage.getFirstErrorMessage(), startsWith(expectedErrorMessage));
        });
    }

    // Scenario: Title and Summary validation ========================================================================
    @When("^I populate the (title|summary) with text longer than the maximum allowed limit of ([0-9]+) characters$")
    public void whenIPopulateTheXWithLongText(String fieldName, int lengthLimit) throws Throwable {

        StringBuilder sb = new StringBuilder();
        for (int i=0; i < lengthLimit+1; i++) {
            sb.append(i % 10);
        }
        String longString = sb.toString();

        // This is compromise between re-usability of "When" statement and clean code
        // You should avoid ugly if statements like the one bellow by all means.
        if (fieldName.equals("title")) {
            contentPage.populatePublicationTitle(longString);
        } else if (fieldName.equals("summary")) {
            contentPage.populatePublicationSummary(longString);
        }
    }

    @When("^I save the publication$")
    public void iSaveThePublication() throws Throwable {
        contentPage.savePublication();
    }

    @Then("^the save is rejected with error message containing \"([^\"]+)\"$")
    public void validationErrorMessageIsShownAndContains(String errorMessageFragment) throws Throwable {

        assertThat("Error message should be shown and contains",
            contentPage.getErrorMessages(),
            hasItem(containsString(errorMessageFragment)));
    }

    // Scenario: Blank attachment field rejection =====================================================================
    @When("^I add an empty upload field$")
    public void iAddAnEmptyUploadField() throws Throwable {
        contentPage.getAttachmentsSection().addUploadField();
    }

    // Scenario: Blank Granularity field rejection =====================================================================
    @When("^I add an empty Granularity field$")
    public void iAddAnEmptyGranularityField() throws Throwable {
        contentPage.getGranularitySection().addGranularityField();
    }

    // Scenario: Blank related link rejection =========================================================================
    @When("^I add an empty related link field$")
    public void iAddAnEmptyRelatedLinkField() throws Throwable {
        contentPage.getRelatedLinksSection().addRelatedLinkField();
    }

}