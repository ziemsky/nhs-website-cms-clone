<#ftl output_format="HTML">
<#include "../../include/imports.ftl">
<#include "../macro/cyberAlertBox.ftl">

<@hst.setBundle basename="rb.doctype.data-security-homepage"/>
<@fmt.message key="headers.alerts" var="cyberAlerts" />
<@fmt.message key="labels.all-alerts" var="cyberAlertsBtn" />
<@fmt.message key="url.all-alerts" var="cyberAlertsBtnUrl" />


<div class="column column--reset" id="${slugify(cyberAlerts)}">
    <div class="cyber-header">
        <div class="cyber-header__group">
            <h2 class="cyber-header__title">${cyberAlerts}</h2>
        </div>

        <div class="cyber-header__cta ctabtn-right"
             aria-labelledby="ctabtn-${slugify(cyberAlertsBtn)}">
            <a href="${cyberAlertsBtnUrl}" class="ctabtn--nhs-digital-button"
               id="ctabtn-${slugify(cyberAlertsBtn)}">${cyberAlertsBtn}</a>
        </div>
    </div>

    <#if pageable?? && pageable.items?has_content>
        <div class="hub-box-list hub-box-list--grid">
            <div class="hub-box-list--grid-row">
                <#list pageable.items as cyberAlert>
                    <#assign item = cyberAlert />
                    <#assign item += {"severityLabel": cyberAlert.getSeverity(), "dateLabel": cyberAlert.getPublishedDate()} />

                    <#assign item += {"grid": true} />
                    <#assign item += {"notOldStyle": true} />

                    <#assign item += {"colSize": pageable.items?size} />
                    <@cyberAlertBox item></@cyberAlertBox>
                </#list>
            </div>
        </div>
    </#if>
</div>
