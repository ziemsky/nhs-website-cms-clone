package uk.nhs.digital.website.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import uk.nhs.digital.common.enums.Region;
import uk.nhs.digital.pagination.Paginated;
import uk.nhs.digital.pagination.Pagination;
import uk.nhs.digital.ps.beans.ExtAttachment;
import uk.nhs.digital.ps.beans.IndexPageImpl;

import java.util.Calendar;
import java.util.List;


@HippoEssentialsGenerated(internalName = "website:publishedwork")
@Node(jcrType = "website:publishedwork")
public class Publishedwork extends CommonFieldsBean implements Paginated {

    @HippoEssentialsGenerated(internalName = "website:InformationType")
    public String[] getInformationType() {
        return getProperty("website:InformationType");
    }

    @HippoEssentialsGenerated(internalName = "website:publicationStyle")
    public String getPublicationStyle() {
        return getProperty("website:publicationStyle");
    }

    @HippoEssentialsGenerated(internalName = "website:image")
    public HippoGalleryImageSet getImage()  {
        return getLinkedBean("website:image", HippoGalleryImageSet.class);
    }

    @HippoEssentialsGenerated(internalName = "website:button")
    public String getButton() {
        return getProperty("website:button");
    }

    @HippoEssentialsGenerated(internalName = "website:coverageEnd")
    public Calendar getCoverageEnd() {
        return getProperty("website:coverageEnd");
    }

    @HippoEssentialsGenerated(internalName = "website:coverageStart")
    public Calendar getCoverageStart() {
        return getProperty("website:coverageStart");
    }

    @HippoEssentialsGenerated(internalName = "website:publicationDate")
    public Calendar getPublicationDate() {
        return getProperty("website:publicationDate");
    }

    @HippoEssentialsGenerated(internalName = "website:geographicCoverage")
    public String[] getGeographicCoverage() {
        return Region.convertGeographicCoverageValues(getProperty("website:geographicCoverage"));
    }

    @HippoEssentialsGenerated(internalName = "website:geographicGranularity")
    public String[] getGeographicGranularity() {
        return getProperty("website:geographicGranularity");
    }

    @HippoEssentialsGenerated(internalName = "website:highlightsTitle")
    public String getHighlightsTitle() {
        return getProperty("website:highlightsTitle");
    }

    @HippoEssentialsGenerated(internalName = "website:highlightsContent")
    public HippoHtml getHighlightsContent() {
        return getHippoHtml("website:highlightsContent");
    }

    @HippoEssentialsGenerated(internalName = "website:isbn")
    public String getIsbn() {
        return getProperty("website:isbn");
    }

    @HippoEssentialsGenerated(internalName = "hippotaxonomy:keys")
    public String[] getKeys() {
        return getProperty("hippotaxonomy:keys");
    }

    @HippoEssentialsGenerated(internalName = "website:issn")
    public String getIssn() {
        return getProperty("website:issn");
    }

    @HippoEssentialsGenerated(internalName = "website:friendlyurls")
    public Friendlyurls getFriendlyurls() {
        return getBean("website:friendlyurls", Friendlyurls.class);
    }

    @HippoEssentialsGenerated(internalName = "website:links")
    public List<HippoBean> getLinks() {
        return getLinkedBeans("website:links", HippoBean.class);
    }

    @HippoEssentialsGenerated(internalName = "website:sections")
    public List<HippoBean> getSections() {
        return getChildBeansByName("website:sections");
    }

    @HippoEssentialsGenerated(internalName = "website:resources")
    public List<ExtAttachment> getResources() {
        return getChildBeansByName("website:resources", ExtAttachment.class);
    }

    @Override
    public Pagination paginate() {
        return new Pagination(null, getLinks().stream().findFirst().map(i -> new IndexPageImpl(i.getDisplayName(), i)).orElse(null));
    }
}
