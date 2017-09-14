package com.example.developer.rssreader.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.developer.rssreader.MainActivity
import com.example.developer.rssreader.R
import com.example.developer.rssreader.RSS
import com.example.developer.rssreader.adapter.BaseAdapter
import com.example.developer.rssreader.adapter.FeedAdapter
import com.example.developer.rssreader.asynctask.LoadRssFeedTask
import com.example.developer.rssreader.model.FeedEntry
import kotlinx.android.synthetic.main.fragment_list_rss.*


class ListRSSFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
        BaseAdapter.ItemClickListener, LoadRssFeedTask.OnFeedLoadedListener {
//    TODO: любые переменные которые не относятся к инстансу, должны быть статическими.
//    В Kotlin статические поля описываются через companion object
//    private val KEY = "datahelper"

    companion object {
        const val KEY_ITEMS = "key_items"
        const val KEY_DATA_LOADED = "key_data_loaded"
    }


//    TODO: lateinit var переменная которая инициализируется позже ее декларации.

    private var isDataLoadedForTheFirstTime = false
    private lateinit var adapter: FeedAdapter

    private var loadRssFeedTask: LoadRssFeedTask? = null

    private val mainActivity: MainActivity? get() = activity as? MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = FeedAdapter(this, savedInstanceState?.getParcelableArrayList(KEY_ITEMS))
        isDataLoadedForTheFirstTime = savedInstanceState?.getBoolean(KEY_DATA_LOADED) ?: false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

//        TODO: В outState мы сохраняем только необходимые данные, то что не возможно будет пересоздать заново, а именно наш фид.
//        Потому как создать новый фид можно только после повторного запроса.
        outState.putParcelableArrayList(KEY_ITEMS, ArrayList(adapter.items))
        outState.putBoolean(KEY_DATA_LOADED, isDataLoadedForTheFirstTime)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        TODO: Зачем инициализировать переменную, если она не используется?
//        val view = inflater.inflate(R.layout.fragment_list_rss, container, false)
//        return view
        return inflater.inflate(R.layout.fragment_list_rss, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        TODO: Опять же, зачем инициализировать переменную, если она не используется?
//        val linearLayoutManager= LinearLayoutManager( activity.applicationContext, LinearLayoutManager.VERTICAL,false)
//        TODO: Использование данного конструктора не целесообразно, так как вызов стандартного конструктора, создает такой же инстанс LayoutManager`а (Vertical & not reversed)
//        recyclerView.layoutManager=linearLayoutManager

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

//        TODO: Нагляднее будет реализовать интерфейс OnRefreshListener в данном фрагменте и выполнять действия по обновлению в нем.
//        swiperefreshlayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
//            refreshItems()
//        })
        swiperefreshlayout.setOnRefreshListener(this)
    }

    override fun onResume() {
        super.onResume()

        if (!isDataLoadedForTheFirstTime) {
            swiperefreshlayout.isRefreshing = true

            onRefresh()
        }
    }

    override fun onRefresh() {
        if (loadRssFeedTask != null) return

        loadRssFeedTask = LoadRssFeedTask(this)
        loadRssFeedTask?.execute(RSS.REDDIT_KOTLIN_FEED)
    }

    override fun onFeedLoaded(feed: List<FeedEntry>?) {
        swiperefreshlayout.isRefreshing = false

        loadRssFeedTask = null

        adapter.updateItems(feed)

        if (!isDataLoadedForTheFirstTime) {
            isDataLoadedForTheFirstTime = feed != null
        }
    }

    override fun onItemClick(position: Int) {
        val feedEntry = adapter.items[position]
        val fragment = NoteRSSFragment.newInstance(feedEntry)

        mainActivity?.replaceMainFragment(fragment, true)
    }

//    TODO: Не правильная реализация загрузки фида (DataHelper).
//    А также не правильно использован adapter для RecyclerView.
//    Нет необходимости задавать новый adapter при изменении данных, он задется один раз и в дальнейшем с его помощью можено заменить контент который отображается в RecyclerView.

//    fun onItemsLoadComplete(list: List<FeedEntry>?) {
//        val adapter = FeedAdapter(list, activity)
//        recyclerView.adapter = adapter
//        adapter.notifyDataSetChanged()
//        swiperefreshlayout.setRefreshing(false)
//    }

}