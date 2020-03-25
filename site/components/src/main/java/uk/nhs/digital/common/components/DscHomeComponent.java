package uk.nhs.digital.common.components;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.builder.HstQueryBuilder;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.nhs.digital.website.beans.CyberAlert;
import uk.nhs.digital.website.beans.DscHome;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DscHomeComponent extends BaseHstComponent {

    private static final Logger log = LoggerFactory.getLogger(TaxonomyFacetWrapper.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        final HstRequestContext ctx = request.getRequestContext();
        DscHome dscHome = ctx.getContentBean(DscHome.class);
        int pageSize = getListSize();
        ArrayList list = new ArrayList(pageSize);
        LocalDateTime now = LocalDateTime.now();

        if (dscHome != null) {

            try {

                HippoBean scope = ctx.getSiteContentBaseBean();
                HstQuery hstCyberAlertQuery = HstQueryBuilder.create(scope)
                    .ofTypes(CyberAlert.class)
                    .limit(pageSize).orderByDescending(
                        now.format(formatter))
                    .build();

                HstQueryResult cyberAlertResult = hstCyberAlertQuery.execute();
                HippoBeanIterator iterator = cyberAlertResult.getHippoBeans();

                while (iterator.hasNext()) {
                    list.add(iterator.next());
                }

                dscHome.setCyberAlerts(list);

            } catch (QueryException qe) {
                log.info(qe.getMessage());
                qe.printStackTrace();

            }

            request.setAttribute("document", dscHome);
        }
    }


    protected int getListSize() {
        //getting the componentPageSize parameter value if defined in the component
        String compononentPageSize = StringUtils
            .defaultIfEmpty(getComponentParameter("alertPageSize"), "");
        //if the componentPageSize hasn't been defined, then use the component param info
        return NumberUtils.isCreatable(compononentPageSize)
            ? Integer.parseInt(compononentPageSize) : 3;
    }

}

