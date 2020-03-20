package uk.nhs.digital.website.beans;

import static org.apache.commons.collections.IteratorUtils.toList;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.builder.HstQueryBuilder;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

import java.util.Calendar;
import java.util.List;

@HippoEssentialsGenerated(internalName = "website:dschomepage")
@Node(jcrType = "website:dschomepage")
public class DscHome extends CommonFieldsBean {

    @HippoEssentialsGenerated(internalName = "website:bannerimagelink")
    public HippoGalleryImageSet getTrustimage() {
        return getLinkedBean("website:bannerimagelink", HippoGalleryImageSet.class);
    }

    @HippoEssentialsGenerated(internalName = "website:usedservicesinternallink")
    public List<HippoBean> getUsedServiceInternalLinks() {
        return getLinkedBeans("website:usedservicesinternallink", HippoBean.class);
    }

    @HippoEssentialsGenerated(internalName = "website:usedservicesexternallink")
    public List<ResourceExternalLink> getUsedServicesExternalLinks() {
        return getLinkedBeans("website:usedservicesexternallink", ResourceExternalLink.class);
    }

    @HippoEssentialsGenerated(internalName = "website:usefulinformationinternallink")
    public List<HippoBean> getUsefulInformationInternalLinks() {
        return getLinkedBeans("website:usefulinformationinternallink", HippoBean.class);
    }

    @HippoEssentialsGenerated(internalName = "website:usefulinformationexternallink")
    public List<ResourceExternalLink> getUsefulInformationExternalLinks() {
        return getLinkedBeans("website:usefulinformationexternallink", ResourceExternalLink.class);
    }

    @HippoEssentialsGenerated(internalName = "website:homepagecategories")
    public List<DscCategory> getHomepageCategories() {
        return getLinkedBeans("website:homepagecategories", DscCategory.class);
    }

    public List<HippoBean> getCaseStudies() throws HstComponentException, QueryException {
        return getCaseStudyList();
    }

    public List<HippoBean> getCaseStudyList() throws QueryException {

        HstRequestContext requestContext = RequestContextProvider.get();
        HippoBean scope = requestContext.getSiteContentBaseBean();

        HstQuery hstBlogQuery = HstQueryBuilder.create(scope)
            .ofTypes(CaseStudy.class).orderByDescending(Calendar.getInstance().toString())
            .limit(2)
            .build();

        HstQueryResult blogResult = hstBlogQuery.execute();
        return toList(blogResult.getHippoBeans());

    }


}
