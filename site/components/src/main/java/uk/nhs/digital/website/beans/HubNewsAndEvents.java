package uk.nhs.digital.website.beans;

import static org.apache.commons.collections.IteratorUtils.toList;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.builder.HstQueryBuilder;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import uk.nhs.digital.ps.beans.HippoBeanHelper;

import java.util.Calendar;
import java.util.List;

@HippoEssentialsGenerated(internalName = "website:hubnewsandevents")
@Node(jcrType = "website:hubnewsandevents")
public class HubNewsAndEvents extends CommonFieldsBean {

    @HippoEssentialsGenerated(internalName = "website:publicationdate")
    public Calendar getPublicationDate() {
        return getProperty("website:publicationdate");
    }

    @HippoEssentialsGenerated(internalName = "website:links")
    public List<HippoBean> getLinks() {
        return getChildBeansByName("website:links");
    }

    @HippoEssentialsGenerated(internalName = "website:socialmedias")
    public SocialMedia getSocialmedias() {
        return getBean("website:socialmedias", SocialMedia.class);
    }

    @HippoEssentialsGenerated(internalName = "website:sections")
    public List<HippoBean> getSections() {
        return getChildBeansByName("website:sections");
    }

    @HippoEssentialsGenerated(internalName = "website:contactdetails")
    public HippoHtml getContactDetails() {
        return getHippoHtml("website:contactdetails");
    }

    @HippoEssentialsGenerated(internalName = "website:gossid")
    public Long getGossid() {
        return getProperty("website:gossid");
    }

    @HippoEssentialsGenerated(internalName = "hippotaxonomy:keys")
    public String[] getKeys() {
        return getProperty("hippotaxonomy:keys");
    }

    public List<String> getFullTaxonomyList() {
        return HippoBeanHelper.getFullTaxonomyList(this);
    }

    @HippoEssentialsGenerated(internalName = "website:friendlyurls")
    public Friendlyurls getFriendlyurls() {
        return getBean("website:friendlyurls", Friendlyurls.class);
    }

    public List<HippoBean> getNewsData() throws HstComponentException, QueryException {
        return getLatestNews(2);
    }

    public List<HippoBean> getBlogData()
        throws HstComponentException, QueryException {
        return getLatestBlog(1);
    }

    public List<HippoBean> getLatestBlog(int numberofBlogsElements) throws QueryException {

        HstRequestContext requestContext = RequestContextProvider.get();
        HippoBean scope = requestContext.getSiteContentBaseBean();

        HstQuery hstBlogQuery = HstQueryBuilder.create(scope)
            .ofTypes(Blog.class)
            .limit(numberofBlogsElements).orderByDescending(
                "website:dateofpublication")
            .build();

        HstQueryResult blogResult = hstBlogQuery.execute();
        return toList(blogResult.getHippoBeans());

    }

    public List<HippoBean> getLatestNews(int numberofNewsElements) throws QueryException {

        HstRequestContext requestContext = RequestContextProvider.get();
        HippoBean scope = requestContext.getSiteContentBaseBean();

        HstQuery hstNewsQuery = HstQueryBuilder.create(scope)
            .ofTypes(News.class)
            .limit(numberofNewsElements).orderByDescending(
                "website:publisheddatetime")
            .build();

        HstQueryResult newsResult = hstNewsQuery.execute();
        return toList(newsResult.getHippoBeans());

    }

}
