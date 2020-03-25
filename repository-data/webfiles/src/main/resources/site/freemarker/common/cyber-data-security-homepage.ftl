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

    <div>
        <@hst.include ref= "banner"/>
    </div>

    <#if hasBannerImage>
        <@hst.link hippobean=document.trustimage fullyQualified=true var="bannerImage" />
        <div class="banner-image banner-image--short article-header"
             aria-label="Document Header"
             style="background-image: url(${bannerImage});">
            <div class="grid-wrapper grid-wrapper--full-width grid-wrapper--wide">
                <div class="article-header--with-icon">
                    <div class="grid-wrapper">
                        <div class="article-header__inner">
                            <div class="grid-row">
                                <div class="column--one-half column--reset">
                                    <h1 class="local-header__title local-header__title--dark"
                                        data-uipath="document.title">${document.title}</h1>
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
            <@hst.include ref="alerts"/>
        </div>

        <div class="grid-row cyber-grid-row">
            <@hst.include ref="most-used"/>
        </div>

        <div class="grid-row cyber-grid-row">
            <@hst.include ref="useful-info"/>
        </div>

        <div class="grid-row cyber-grid-row">
            <@hst.include ref="modules"/>
        </div>

        <div class="grid-row cyber-grid-row">
            <@hst.include ref="case-studies"/>
        </div>

    </div>

</article>
