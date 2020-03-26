package uk.nhs.digital.website.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;


@HippoEssentialsGenerated(internalName = "website:fullwidthimage")
@Node(jcrType = "website:fullwidthimage")
public class FullWidthImage extends BaseCompound {

    @HippoEssentialsGenerated(internalName = "website:title")
    public String getTitle() {
        return getProperty("website:title");
    }

    @HippoEssentialsGenerated(internalName = "website:imagealt")
    public String getImagealt() {
        return getProperty("website:imagealt");
    }

    @HippoEssentialsGenerated(internalName = "website:image")
    public HippoGalleryImageSet getImage() {
        return getLinkedBean("website:image", HippoGalleryImageSet.class);
    }
}
