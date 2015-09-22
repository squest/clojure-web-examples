(ns webbeta.pages
  (:require
    [hiccup.core :as hc]
    [hiccup.page :refer [html5 include-css include-js]]
    [hiccup.form :as form]
    [webbeta.articles :as article]))

(defn- head
  [title]
  [:head
   [:meta {:charset "utf-8"}]
   (include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css")
   (include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css")
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   [:title title]])

(defn- header
  []
  [:header
   [:div.row
    [:div.col-lg-5
     [:h2 "Some bloc's blog"]
     [:h5 [:i "Lost in the universe"]]]
    [:div.col-lg-7.clearfix
     [:div.pull-right
      [:a {:href "/"} "Homepage"]
      [:span " | "]
      [:a {:href "/add-article"} "Add a new article"]]]]
   [:hr]])

(defn- content
  [& content]
  [:body.container
   [:div.row
    content]])

(defn- content-homepage
  []
  (let [data (article/all-articles)]
    [:div.col-xl-12
     [:div.col-xl-5
      [:h3 "Available articles for sale"]
      [:ul
       (for [d data]
         [:li
          [:a {:href (str "/article/" (:id d))}
           (:title d)]])]]]))

(defn- footer
  []
  [:footer
   [:hr]
   [:p "Copyright 2015 Jojon dkk"]])

(defn home []
  (html5
    (head "This is the homepage")
    (content (header)
             (content-homepage)
             (footer))))

(defn add-article []
  (html5
    (head "Add a new article")
    (content (header)
             [:div.row
              [:div.col-md-offset-2.col-md-5
               [:form {:action "/add-article" :method "post"}
                [:fieldset
                 [:legend "Add an article"]
                 [:input {:type "text"
                          :name "title"
                          :placeholder "Title"
                          :size "50"}]
                 [:br] [:br]
                 [:textarea {:name "text"
                             :class "form-control"
                             :rows "6"}]
                 [:br]
                 [:input {:type  "submit"
                          :class "btn btn-primary pull-right"}]]]]]
             (footer))))

(defn- article-sidebar
  []
  [:div.col-md-3
   [:ul [:h4 "Available articles"]
    (for [d (article/all-articles)]
      [:li
       [:a {:href (str "/article/" (:id d))}
        (:title d)]])]])

(defn- single-article
  [data]
  (let [{:keys [title text]} data]
    [:div.col-md-8
     [:h4 title]
     [:p text]]))

(defn article [id]
  (let [data (article/article id)]
    (html5
      (head (:title data))
      (content (header)
               [:div.row
                (article-sidebar)
                (single-article data)]
               (footer)))))




















