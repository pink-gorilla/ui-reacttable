
(rf/dispatch [:css/set-theme-component :tailwind-full "light"])
(rf/dispatch [:css/set-theme-component :tailwind-girouette false])

(defn link-href [href text]
  [:a.bg-blue-300.cursor-pointer.hover:bg-red-700.m-1
   {:href href} text])

(defn link-fn [fun text]
  [:a.bg-blue-300.cursor-pointer.hover:bg-red-700.m-1
   {:on-click fun} text])

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
    [example]
    [react-table {:data data :columns headers}]
    [react-table {:data data-big :columns headers :style {:width "100%" :overflow "scroll"}}]]])

(add-page grid-page :user/grid)