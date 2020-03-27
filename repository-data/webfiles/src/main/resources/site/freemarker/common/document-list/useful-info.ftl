<#ftl output_format="HTML">
<#include "../../include/imports.ftl">
<#include "../macro/cyberAlertBox.ftl">

<@hst.setBundle basename="rb.doctype.data-security-homepage"/>
<@fmt.message key="headers.useful-info" var="usefulInfo" />
<@fmt.message key="labels.all-useful-info" var="usefulInfoBtn" />
<@fmt.message key="url.all-useful-info" var="usefulInfoBtnUrl" />

<div class="grid-row cyber-grid-row">
    <div class="column column--reset" id="${slugify(usefulInfo)}">
        <div class="cyber-header">
            <div class="cyber-header__group">
                <h2 class="cyber-header__title">${usefulInfo}</h2>
            </div>

            <div class="cyber-header__cta ctabtn-right"
                 aria-labelledby="ctabtn-${slugify(usefulInfoBtn)}">
                <a href="${usefulInfoBtnUrl}" class="ctabtn--nhs-digital-button"
                   id="ctabtn-${slugify(usefulInfoBtn)}">${usefulInfoBtn}</a>
            </div>
        </div>

        <#if pageable?? && pageable.items?has_content>
            <div class="hub-box-list hub-box-list--grid">
                <div class="hub-box-list--grid-row">
                    <#list pageable.items as usefulInfoData>
                        <#assign item = usefulInfoData />
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
