<#ftl output_format="HTML">
<#include "../../include/imports.ftl">
<#include "../macro/cyberAlertBox.ftl">

<@hst.setBundle basename="rb.doctype.data-security-homepage"/>
<@fmt.message key="headers.used-services" var="mostUsedServices" />
<@fmt.message key="labels.all-services" var="mostUsedServicesBtn" />
<@fmt.message key="url.all-services" var="mostUsedServicesBtnUrl" />

<div class="grid-row cyber-grid-row">
    <div class="column column--reset" id="${slugify(mostUsedServices)}">
        <div class="cyber-header">
            <div class="cyber-header__group">
                <h2 class="cyber-header__title">${mostUsedServices}</h2>
            </div>

            <div class="cyber-header__cta ctabtn-right"
                 aria-labelledby="ctabtn-${slugify(mostUsedServicesBtn)}">
                <a href="${mostUsedServicesBtnUrl}"
                   class="ctabtn--nhs-digital-button"
                   id="ctabtn-${slugify(mostUsedServicesBtn)}">${mostUsedServicesBtn}</a>
            </div>
        </div>


        <#if pageable?? && pageable.items?has_content>
            <div class="hub-box-list hub-box-list--grid">
                <div class="hub-box-list--grid-row">
                    <#list pageable.items as serviceData>
                        <#assign item = serviceData />
                        <@hst.link hippobean=item var="itemLink" />
                        <#assign item += {"link": itemLink} />

                        <#assign item += {"grid": true} />
                        <#assign item += {"newStyle": true} />
                        <#assign item += {"colSize": pageable.items?size} />
                        <@cyberAlertBox item></@cyberAlertBox>
                    </#list>
                </div>
            </div>
        </#if>
    </div>
</div>
