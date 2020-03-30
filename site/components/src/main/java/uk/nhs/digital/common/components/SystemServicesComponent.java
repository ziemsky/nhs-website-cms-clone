package uk.nhs.digital.common.components;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.cms7.essentials.components.CommonComponent;
import org.onehippo.cms7.essentials.components.info.EssentialsListComponentInfo;
import uk.nhs.digital.website.beans.PrimarySectionVisualHub;
import uk.nhs.digital.website.beans.VisualHub;

import java.util.ArrayList;
import java.util.List;

/**
 * We are not extending "EssentialsSearchComponent" because we could not find a elegant way of using our own search
 * HstObject in the faceted search.
 */
@ParametersInfo(type = EssentialsListComponentInfo.class)
public class SystemServicesComponent extends CommonComponent {

    private static final String INTIAL_SERVICES_VIEW = "Incident Management";

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        final HstRequestContext ctx = request.getRequestContext();
        VisualHub visualHub = (VisualHub) ctx.getContentBean();

        List<String> elementNameList = new ArrayList<>();

        for (HippoBean primarySectionVisualHub : visualHub.getPrimarySections()) {
            PrimarySectionVisualHub hub = (PrimarySectionVisualHub)primarySectionVisualHub;
            String heading = hub.getPrimarySectionHeading() != null ? hub.getPrimarySectionHeading() : INTIAL_SERVICES_VIEW;

            request.setAttribute(heading, hub.getTileSections());
            elementNameList.add(heading);

            request.setAttribute(heading + " Links",
                hub.getPrimarySectionsLinks());
            elementNameList.add(heading + " Links");
        }

        request.setAttribute("attributeNames", elementNameList);
        request.setAttribute("document", visualHub);

    }

}
