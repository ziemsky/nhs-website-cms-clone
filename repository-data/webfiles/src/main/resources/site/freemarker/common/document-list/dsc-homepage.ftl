<#ftl output_format="HTML">
<#include "../../include/imports.ftl">
<#include "../macro/cyberAlertBox.ftl">

<#-- @ftlvariable name="wrappingDocument" type="uk.nhs.digital.website.beans.Calltoaction" -->
<@hst.setBundle basename="rb.doctype.data-security-homepage"/>
<@fmt.message key="headers.useful-info" var="usefulInfo" />
<@fmt.message key="labels.all-useful-info" var="usefulInfoBtn" />
<@fmt.message key="url.all-useful-info" var="usefulInfoBtnUrl" />

<div class="grid-row cyber-grid-row">
    <div class="column column--reset" id="${slugify(wrappingDocument.getTitle())}">
        <div class="cyber-header">
            <div class="cyber-header__group">
                <h2 class="cyber-header__title">${wrappingDocument.getTitle()}</h2>
            </div>

            <#if document.internal?has_content>
                <@hst.link var="link" hippobean=document.internal/>
            <#else>
                <#assign link=document.external/>
            </#if>

            <div class="cyber-header__cta ctabtn-right"
                 aria-labelledby="ctabtn-${slugify(wrappingDocument.getLabel())}">
                <a href="${link}" class="ctabtn--nhs-digital-button"
                   id="ctabtn-${slugify(wrappingDocument.getLabel())}">${wrappingDocument.getLabel()}</a>
            </div>
        </div>

        <#if pageable?? && pageable.items?has_content>
            <div class="hub-box-list hub-box-list--grid">
                <#assign index=0/>
                <#list pageable.items?chunk(3) as row>
                    <div class="hub-box-list--grid-row">
                        <#list pageable.items as itemData>
                            <#assign item = itemData />
                            <@hst.link hippobean=item var="itemLink" />
                            <#assign item += {"link": itemLink} />

                            <#assign item += {"grid": true} />
                            <#assign item += {"newStyle": true} />
                            <#assign item += {"colSize": row?size} />

                            <#assign key = slugify(wrappingDocument.getTitle()) + "-" + index + "-" + itemData?index />
                            <#assign item += {"key":  key}/>

                            <@cyberAlertBox item></@cyberAlertBox>
                        </#list>
                    </div>
                    <#assign index += 1/>
                </#list>
            </div>
        </#if>
    </div>
</div>
