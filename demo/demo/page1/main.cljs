(ns demo.page1.main
  (:require
   [goldly.page :as page]
   [demo.lib.ui :refer [link-href]]))

; main page 

(defn  demo-page [{:keys [handler route-params query-params] :as route}]
  [:div
   [:h1.text-2xl.text-red-600.m-5 "demo user app"]
   [link-href "devtools/help" "goldly developer tools"]
   [link-href "/grid" "grid"]])

(page/add demo-page :user/main)