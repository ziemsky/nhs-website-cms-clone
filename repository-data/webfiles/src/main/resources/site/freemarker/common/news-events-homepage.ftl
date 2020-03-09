<#ftl output_format="HTML">

<#include "../include/imports.ftl">
<#include "macro/documentHeader.ftl">
<#include "macro/metaTags.ftl">
<#include "macro/stickyNavSections.ftl">
<#include "macro/stickyNavTags.ftl">
<#include "macro/hubBox.ftl">
<#include "macro/sections/sections.ftl">

<#-- Add meta tags -->
<@metaTags></@metaTags>

<#--Resource Bundle-->
<@hst.setBundle basename="rb.doctype.news-events-homepage"/>
<@fmt.message key="headers.news" var="latestNews"/>
<@fmt.message key="label.all-news" var="viewAllNews"/>
<@fmt.message key="headers.blogs" var="latestBlogs"/>
<@fmt.message key="label.all-blogs" var="viewAllBlogs"/>
<@fmt.message key="headers.contact" var="contactUs"/>
<@fmt.message key="headers.follow" var="followUs"/>


<#--Modifiers-->
<#assign noBorder = {"noBorder": true} />
<#assign noPadding = {"noPadding": true} />
<#assign noBackgroundCol = {"noBackgroundCol": true} />
<#assign sameStyle = {"sameStyle": true} />


<#if document.socialmedia.othersocialmedias?has_content>
    <#list document.socialmedia.othersocialmedias as other>
        <#assign facebookSocial = (other.title?lower_case == 'facebook')?then(other,{}) />
    </#list>
</#if>

<#assign hasSocialMedia = document.socialmedia.linkedinlink?has_content || document.socialmedia.twitteruser?has_content || facebookSocial?has_content/>

<article class="article article--news-hub">
    <@documentHeader document 'news-events-homepage' '' '' '' '' false></@documentHeader>

    <div class="grid-wrapper grid-wrapper--article" aria-label="Document Content">
        <div class="grid-row">
            <div class="column column--one-quarter page-block page-block--sidebar">
                <div id="sticky-nav">
                    <#assign links = [] />

                    <#if document.newsData?has_content>
                        <#assign links += [{ "url": "#" + slugify(latestNews), "title": latestNews }] />
                    </#if>
                    <#if document.blogData?has_content>
                        <#assign links += [{ "url": "#" + slugify(latestBlogs), "title": latestBlogs }] />
                    </#if>
                    <#if document.sections?has_content>
                        <#assign links += getNavLinksInMultiple(document.sections) />
                    </#if>
                    <#if document.contactDetails?has_content>
                        <#assign links += [{ "url": "#" + slugify(contactUs), "title": contactUs }] />
                    </#if>
                    <#if hasSocialMedia>
                        <#assign links += [{ "url": "#" + slugify(followUs), "title": followUs }] />
                    </#if>

                    <@stickyNavSections getStickySectionNavLinks({"sections": links})></@stickyNavSections>
                </div>
            </div>

            <div class="column column--three-quarters page-block page-block--main">
                <#if document.newsData?has_content>
                    <div class="article-section article-section--letter-group" id="${slugify(latestNews)}">
                        <h2>${latestNews}</h2>
                        <div class="grid-row">
                            <div class="column column--reset">
                                <div class="hub-box-list bottom-margin-20" id="${slugify(latestNews)}-list">
                                    <#list document.newsData as news>
                                        <#assign item = news />

                                        <#assign item = { "title": item.title, "text": item.shortsummary} />

                                        <@hst.link hippobean=news var="newsLink" />
                                        <@fmt.formatDate value=news.publisheddatetime.time type="Date" pattern="EEEE d MMMM yyyy" timeZone="${getTimeZone()}" var="date" />

                                        <#assign item += { "link": newsLink, "date": date } />
                                        <#assign item += { "imagesection": news.leadimagesection?has_content?then(news.leadimagesection, "EMPTY")} />

                                    <#--                                        <#assign item += noBorder + noBackgroundCol + noPadding />-->
                                        <@hubBox item ></@hubBox>
                                    </#list>
                                </div>
                            </div>
                        </div>
                        <div class="grid-row bottom-margin-20">
                            <#--TODO: link to all news -->
                            <a href="#">${viewAllNews}</a>
                        </div>
                    </div>
                </#if>

                <#if document.blogData?has_content>
                    <div class="article-section article-section--letter-group" id="${slugify(latestBlogs)}">
                        <h2>${latestBlogs}</h2>
                        <div class="grid-row">
                            <div class="column column--reset">
                                <div class="hub-box-list bottom-margin-20" id="${slugify(latestBlogs)}-list">
                                    <#list document.blogData as blog>
                                        <#assign item = blog />

                                        <#assign item = { "title": item.title, "text": item.shortsummary} />

                                        <@hst.link hippobean=blog var="blogLink" />

                                        <@fmt.formatDate value=blog.dateOfPublication.time type="Date" pattern="EEEE d MMMM yyyy" timeZone="${getTimeZone()}" var="date" />

                                        <#assign item += { "link": blogLink, "date": date } />
                                        <#assign item += { "imagesection": blog.leadimagesection?has_content?then(blog.leadimagesection, "EMPTY")} />

                                        <#assign item += sameStyle/>
                                    <#--                                        <#assign item += noBorder + noBackgroundCol + noPadding + sameStyle />-->
                                        <@hubBox item ></@hubBox>
                                    </#list>
                                </div>
                            </div>
                        </div>
                        <div class="grid-row bottom-margin-20">
                            <#--TODO: link to all blogs -->
                            <a href="#">${viewAllBlogs}</a>
                        </div>
                    </div>
                </#if>

                <#if document.sections?has_content>
                    <div class="article-section">
                        <@sections document.sections></@sections>
                    </div>
                </#if>

                <#if document.contactDetails?has_content>
                    <div class="article-section article-section--letter-group" id="${slugify(contactUs)}">
                        <h2>${contactUs}</h2>
                        <div class="grid-row">
                            <div class="column column--reset bottom-margin-20">
                                <@hst.html hippohtml=document.contactDetails contentRewriter=gaContentRewriter/>
                            </div>
                        </div>
                    </div>
                </#if>

                <#if hasSocialMedia>
                    <div class="article-section article-section--letter-group" id="${slugify(followUs)}">
                        <h2>${followUs}</h2>
                        <div class="grid-row">
                            <div class="column column--reset">
                                <div class="blog-social">
                                    <#if facebookSocial?has_content >
                                        <div class="blog-social-icon like-first-child">
                                            <a target="_blank" href="${facebookSocial.link}">
                                                <img src="<@hst.webfile path="/images/icon/Facebook.svg"/>" alt="Follow on Facebook" class="blog-social-icon__img" />
                                            </a>
                                        </div>
                                    </#if>

                                    <#if document.socialmedia.twitteruser?has_content>
                                        <div class="blog-social-icon like-first-child">
                                            <a target="_blank" href="https://twitter.com/${document.socialmedia.twitteruser}">
                                                <img src="<@hst.webfile path="/images/icon/Twitter.svg"/>" alt="Follow on Twitter" class="blog-social-icon__img" />
                                            </a>
                                        </div>
                                    </#if>

                                    <#if document.socialmedia.linkedinlink?has_content>
                                        <div class="blog-social-icon like-first-child">
                                            <a target="_blank" href="${document.socialmedia.linkedinlink}">
                                                <img src="<@hst.webfile path="/images/icon/LinkedIn.svg"/>" alt="Follow on LinkedIn" class="blog-social-icon__img" />
                                            </a>
                                        </div>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</article>
