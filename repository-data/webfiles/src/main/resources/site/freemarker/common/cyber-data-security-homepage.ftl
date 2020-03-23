<#ftl output_format="HTML">
<#include "../include/imports.ftl">
<#include "macro/visualhubBox.ftl">
<#include "macro/cyberAlertBox.ftl">
<#include "macro/hubBox.ftl">
<#include "macro/sections/quoteSection.ftl">
<#include "macro/documentHeader.ftl">

<@hst.setBundle basename="rb.doctype.data-security-homepage"/>

<#-- Add meta tags -->
<#include "../common/macro/metaTags.ftl">
<@metaTags></@metaTags>

<@fmt.message key="headers.alerts" var="cyberAlerts" />
<@fmt.message key="headers.case-studies" var="caseStudies" />
<@fmt.message key="headers.used-services" var="mostUsedServices" />
<@fmt.message key="headers.useful-info" var="usefulInfo" />
<@fmt.message key="labels.alert-date" var="alertsDateLabel" />
<@fmt.message key="labels.alert-severity" var="alertsSeverityLabel" />
<@fmt.message key="labels.all-alerts" var="cyberAlertsBtn" />
<@fmt.message key="labels.all-case-studies" var="caseStudiesBtn" />
<@fmt.message key="labels.all-services" var="mostUsedServicesBtn" />
<@fmt.message key="labels.all-useful-info" var="usefulInfoBtn" />
<@fmt.message key="url.all-alerts" var="cyberAlertsBtnUrl" />
<@fmt.message key="url.all-case-studies" var="caseStudiesBtnUrl" />
<@fmt.message key="url.all-services" var="mostUsedServicesBtnUrl" />
<@fmt.message key="url.all-useful-info" var="usefulInfoBtnUrl" />

<#assign hasBannerImage = document.trustimage?has_content />

<article class="article">
    <#if hasBannerImage>
        <@hst.link hippobean=document.trustimage fullyQualified=true var="bannerImage" />
        <div class="banner-image banner-image--short article-header" aria-label="Document Header" style="background-image: url(${bannerImage});">
            <div class="grid-wrapper grid-wrapper--full-width grid-wrapper--wide">
                <div class="article-header--with-icon">
                    <div class="grid-wrapper">
                        <div class="article-header__inner">
                            <div class="grid-row">
                                <div class="column--one-half column--reset">
                                    <h1 class="local-header__title local-header__title--dark" data-uipath="document.title">${document.title}</h1>
                                    <p class="article-header__subtitle article-header__subtitle--dark">
                                        <@hst.html hippohtml=document.shortsummary contentRewriter=gaContentRewriter/>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <#else>
        <@documentHeader document 'cyber-data-security-homepage'></@documentHeader>
    </#if>

    <div class="grid-wrapper">
        <div class="grid-row cyber-grid-row">
            <div class="column column--reset" id="${slugify(cyberAlerts)}">
                <div class="cyber-header">
                    <div class="cyber-header__group">
                        <h2 class="cyber-header__title">${cyberAlerts}</h2>
                    </div>

                    <div class="cyber-header__cta ctabtn-right" aria-labelledby="ctabtn-${slugify(cyberAlertsBtn)}">
                        <a href="${cyberAlertsBtnUrl}" class="ctabtn--nhs-digital-button" id="ctabtn-${slugify(cyberAlertsBtn)}">${cyberAlertsBtn}</a>
                    </div>
                </div>

                <#if document.cyberAlertsList?has_content>
                    <div class="hub-box-list hub-box-list--grid">
                        <div class="hub-box-list--grid-row">
                            <#list document.cyberAlertsList as cyberAlert >
                                <#assign item = cyberAlert />
                                <#assign item += {"severityLabel": alertsSeverityLabel, "dateLabel": alertsDateLabel} />

                                <#assign item += {"grid": true} />
                                <#assign item += {"notOldStyle": true} />

                                <#assign item += {"colSize": document.cyberAlertsList?size} />
                                <@cyberAlertBox item></@cyberAlertBox>
                            </#list>
                        </div>
                    </div>
                </#if>
            </div>
        </div>

        <div class="grid-row cyber-grid-row">
            <div class="column column--reset" id="${slugify(mostUsedServices)}">
                <div class="cyber-header">
                    <div class="cyber-header__group">
                        <h2 class="cyber-header__title">${mostUsedServices}</h2>
                    </div>

                    <div class="cyber-header__cta ctabtn-right" aria-labelledby="ctabtn-${slugify(mostUsedServicesBtn)}">
                        <a href="${mostUsedServicesBtnUrl}" class="ctabtn--nhs-digital-button" id="ctabtn-${slugify(mostUsedServicesBtn)}">${mostUsedServicesBtn}</a>
                    </div>
                </div>

                <#if document.serviceDataList?has_content>
                    <div class="hub-box-list hub-box-list--grid">
                        <div class="hub-box-list--grid-row">
                            <#list document.serviceDataList as serviceData >
                                <#assign item = serviceData />
                                <#assign item += {"grid": true} />
                                <#assign item += {"notOldStyle": true} />
                                <#assign item += {"colSize": document.serviceDataList?size} />
                                <@cyberAlertBox item></@cyberAlertBox>
                            </#list>
                        </div>
                    </div>
                </#if>
            </div>
        </div>

        <div class="grid-row cyber-grid-row">
            <div class="column column--reset" id="${slugify(usefulInfo)}">
                <div class="cyber-header">
                    <div class="cyber-header__group">
                        <h2 class="cyber-header__title">${usefulInfo}</h2>
                    </div>

                    <div class="cyber-header__cta ctabtn-right" aria-labelledby="ctabtn-${slugify(usefulInfoBtn)}">
                        <a href="${usefulInfoBtnUrl}" class="ctabtn--nhs-digital-button" id="ctabtn-${slugify(usefulInfoBtn)}">${usefulInfoBtn}</a>
                    </div>
                </div>

                <#if document.usefulInfoDataList?has_content>
                    <div class="hub-box-list hub-box-list--grid">
                        <div class="hub-box-list--grid-row">
                            <#list document.usefulInfoDataList as usefulInfoData >
                                <#assign item = usefulInfoData />
                                <#assign item += {"grid": true} />
                                <#assign item += {"notOldStyle": true} />
                                <#assign item += {"colSize": document.usefulInfoDataList?size} />
                                <@cyberAlertBox item></@cyberAlertBox>
                            </#list>
                        </div>
                    </div>
                </#if>
            </div>
        </div>

        <#if document.categoryList?has_content>
            <#list document.categoryList as item>
                <div class="grid-row cyber-grid-row">
                    <div class="column column--reset" id="${slugify(item.title)}">
                        <div class="cyber-header">
                            <div class="cyber-header__group">
                                <h2 class="cyber-header__title">${item.title}</h2>
                                <p class="cyber-header__content">${item.content}</p>
                            </div>

                            <div class="cyber-header__cta ctabtn-right" aria-labelledby="ctabtn-${slugify(item.label)}">
                                <a href="${item.url}" class="ctabtn--nhs-digital-button" id="ctabtn-${slugify(item.label)}">${item.label}</a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </#if>

        <div class="grid-row cyber-grid-row">
            <div class="column column--reset" id="${slugify(caseStudies)}">
                <div class="cyber-header">
                    <div class="cyber-header__group">
                        <h2 class="cyber-header__title">${caseStudies}</h2>
                    </div>

                    <div class="cyber-header__cta ctabtn-right" aria-labelledby="ctabtn-${slugify(caseStudiesBtn)}">
                        <a href="${caseStudiesBtnUrl}" class="ctabtn--nhs-digital-button" id="ctabtn-${slugify(caseStudiesBtn)}">${caseStudiesBtn}</a>
                    </div>
                </div>

                <#if document.caseStudies?has_content>
                    <div class="quote-box-list--grid">
                        <div class="quote-box-list--grid-row">
                            <#list document.caseStudies as caseStudy >
                                <#assign item = caseStudy />
                                <#assign item += {"quote": caseStudy.leadParagraph, "person":caseStudy.authorName, "role":caseStudy.authorJobTitle, "organisation":caseStudy.authorOrganisation} />
                                <#assign item += {"grid": true} />
                                <#assign item += {"notOldStyle": true} />
                                <div class="quote-box__container quote-box--col-${document.caseStudies?size}">
                                    <@quoteSection item/>
                                </div>
                            </#list>
                        </div>
                    </div>
                </#if>
            </div>
        </div>

    </div>

</article>
