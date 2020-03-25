package uk.nhs.digital.website.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

import java.util.List;

@HippoEssentialsGenerated(internalName = "website:tilesectionvisualhub")
@Node(jcrType = "website:tilesectionvisualhub")
public class TileSection extends CommonFieldsBean {

    @HippoEssentialsGenerated(internalName = "website:sectionheading")
    public String getSectionHeading() {
        return getProperty("website:sectionheading");
    }

    @HippoEssentialsGenerated(internalName = "website:sectionintroduction")
    public HippoHtml getSectionIntroduction() {
        return getHippoHtml("website:sectionintroduction");
    }

    @HippoEssentialsGenerated(internalName = "website:tilesectionlinks")
    public List<HippoBean> getLinks() {
        return getChildBeansByName("website:tilesectionlinks");
    }

}
