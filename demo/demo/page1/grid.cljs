(ns demo.page1.grid
  (:require
   [goldly.page :as page]
   [ui.reacttable :refer [react-table]]
   [demo.lib.ui :refer [link-href]]))

(def data
  [{:firstName "John" :lastName "Doe" :age 53 :Status "web surfing"}
   {:firstName "John" :lastName "Doe Senior" :age 73 :Status "wind surfing"}])

(defn one [i]
  {:firstName "John" :lastName "Doe" :age i :Status "web surfing"})

(def data-big
  (into
   []
   (map one (range 100))))

(def headers
  [{:Header "Name"
    :columns [{:Header "First Name"
               :accessor "firstName"}
              {:Header "Last Name"
               :accessor "lastName"}]}
   {:Header "Info"
    :columns [{:Header "Age"
               :accessor "age"}
              {:Header "Visits"
               :accessor "visits"}
              {:Header "Status"
               :accessor "status"}
              {:Header "Profile Progress"
               :accessor "progress"}]}])

(defn  grid-page [{:keys [handler route-params query-params] :as route}]
  [:div.w-screen.h-screen.bg-red-300
   ;[:div.text-green-300 "grid1"]
   [:div.grid.grid-cols-2.w-full.h-full.bg-blue-300.overflow-scroll
    [:div [link-href "/" "main"]]
    ;[example]
    [react-table {:data data :columns headers}]
    [react-table {:data data-big :columns headers :style {:width "100%" :overflow "scroll"}}]]])

(page/add grid-page :user/grid)