package ru.s1aks.translator.view.history

import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.s1aks.translator.databinding.ActivityHistoryBinding
import ru.s1aks.translator.model.data.AppState
import ru.s1aks.translator.model.data.DataModel
import ru.s1aks.translator.utils.convertMeaningsToString
import ru.s1aks.translator.view.base.BaseActivity
import ru.s1aks.translator.view.descriptionscreen.DescriptionActivity

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override val model: HistoryViewModel by viewModel()
    private val adapter: HistoryAdapter by lazy { HistoryAdapter(onListItemClickListener) }
    private val onListItemClickListener: HistoryAdapter.OnListItemClickListener =
        object : HistoryAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@HistoryActivity,
                        data.text!!
                    )
                )
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToView(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        model.subscribe().observe(this, { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}
