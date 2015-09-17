(ns webbeta.pages
  (:require
    [hiccup.core :as hc]
    [hiccup.page :as hp]))

(defn- head
  []
  (hc/html [:head
            [:meta {:charset "utf-8"}]
            (hp/include-css "/css/normalize.css")
            (hp/include-css "/css/foundation.min.css")
            (hp/include-js "/js/vendor/jquery.js")
            (hp/include-js "/js/foundation.min.js")]))

(defn- header
  []
  (hc/html [:header {:class "large-12 large-centered columns"}
            [:h2 "Some logo"]
            [:hr]]))

(defn- footer
  []
  (hc/html [:footer
            [:hr]
            [:p "Copyright 2015 Jojon dkk"]]))

(defn- flat-thing
  [title some-fake-image]
  (hc/html
    [:div.large-4.column
     [:h4 title]
     [:p some-fake-image]]))

(defn- body
  []
  (hc/html [:div {:class "large-12"}
            [:h1 "Some article"]
            [:div {:class "large-12 columns"}
             (map flat-thing
                  ["First title" "Second title" "Third title"]
                  ["fake one" "fake two" "fake three"])
             (for [i (map vector
                          ["First title" "Second title" "Third title"]
                          ["fake one" "fake two" "fake three"])]
               (apply flat-thing i))]]))

(defn home
  ([] (hp/html5 (head)
                [:body {:class "row"}
                 (header)
                 (body)
                 (footer)]))
  ([nama] (hp/html5 (head)
                [:body {:class "row"}
                 (header)
                 [:h2 (str "Hello " nama)]
                 (body)
                 (footer)])))
















