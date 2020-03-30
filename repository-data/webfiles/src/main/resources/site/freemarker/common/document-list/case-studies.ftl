<#ftl output_format="HTML">
<#include "../../include/imports.ftl">
<#include "../macro/cyberAlertBox.ftl">
<#include "../macro/sections/quoteSection.ftl">

<@hst.setBundle basename="rb.doctype.data-security-homepage"/>
<@fmt.message key="headers.case-studies" var="caseStudies" />
<@fmt.message key="labels.all-case-studies" var="caseStudiesBtn" />
<@fmt.message key="url.all-case-studies" var="caseStudiesBtnUrl" />


<div class="grid-row cyber-grid-row">
    <div class="column column--reset" id="${slugify(caseStudies)}">
        <div class="cyber-header">
            <div class="cyber-header__group">
                <h2 class="cyber-header__title">${caseStudies}</h2>
            </div>

            <div class="cyber-header__cta ctabtn-right"
                 aria-labelledby="ctabtn-${slugify(caseStudiesBtn)}">
                <a href="${caseStudiesBtnUrl}"
                   class="ctabtn--nhs-digital-button"
                   id="ctabtn-${slugify(caseStudiesBtn)}">${caseStudiesBtn}</a>
            </div>
        </div>

        <#if pageable?? && pageable.items?has_content>
            <div class="quote-box-list--grid">
                <#list pageable.items?chunk(2) as row>
                    <div class="quote-box-list--grid-row">
                        <#list row as caseStudy>
                            <#assign item = caseStudy />
                            <#assign item += {"quote": caseStudy.summary, "person":caseStudy.authorName, "role":caseStudy.authorJobTitle, "organisation":caseStudy.authorOrganisation} />
                            <#assign item += {"grid": true} />
                            <#assign item += {"newStyle": true} />
                            <div class="quote-box__container quote-box--col-${row?size}">
                                <@quoteSection item/>
                            </div>
                        </#list>
                    </div>
                </#list>
            </div>
        </#if>
    </div>
</div>



