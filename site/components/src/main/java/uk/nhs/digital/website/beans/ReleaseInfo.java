package uk.nhs.digital.website.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

import java.util.Calendar;
import java.util.List;

@HippoEssentialsGenerated(internalName = "website:releaseinfo")
@Node(jcrType = "website:releaseinfo")
public class ReleaseInfo extends HippoCompound {

    public String getSectionType() {
        return "releaseinfo";
    }

    @HippoEssentialsGenerated(internalName = "website:version")
    public String getVersion() {
        return getProperty("website:version");
    }

    @HippoEssentialsGenerated(internalName = "website:releasedate")
    public Calendar getReleasedate() {
        return getProperty("website:releasedate");
    }

    @HippoEssentialsGenerated(internalName = "website:effectivedate")
    public Calendar getEffectivedate() {
        return getProperty("website:effectivedate");
    }
    
    @HippoEssentialsGenerated(internalName = "website:otherversionreference")
    public String getOtherversionreference() {
        return getProperty("website:otherversionreference");
    }
    
    @HippoEssentialsGenerated(internalName = "website:changesummary")
    public String getChangesummary() {
        return getProperty("website:changesummary");
    }

    @HippoEssentialsGenerated(internalName = "website:releasestatus")
    public String getReleasestatus() {
        return getProperty("website:releasestatus");
    }

    @HippoEssentialsGenerated(internalName = "website:replacementlink")
    public List<?> getReplacementlink() {
        return getChildBeansByName("website:replacementlink");
    }
}
