(ns webbeta.pages
  (:require
    [hiccup.core :as hc]
    [hiccup.page :as hp]))

(defn home
  ([] (hp/html5
        [:head
         [:meta {:charset "utf-8"}]
         (hp/include-css "/css/normalize.css")
         (hp/include-css "/css/foundation.min.css")]
        [:body
         [:center
          [:h1 "Hellooow world!"]]]))
  ([nama]
   (hp/html5
     [:head
      [:meta {:charset "utf-8"}]
      (hp/include-css "/css/normalize.css")
      (hp/include-css "/css/foundation.min.css")]
     [:body
      [:center
       [:h1 (str "Hello " nama)]]])))
