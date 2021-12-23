package ru.s1aks.translator.view.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.s1aks.translator.R
import ru.s1aks.translator.databinding.ActivityMainBinding
import ru.s1aks.translator.model.data.AppState
import ru.s1aks.translator.model.data.DataModel
import ru.s1aks.translator.utils.network.OnlineLiveData
import ru.s1aks.translator.view.base.BaseActivity
import ru.s1aks.translator.view.descriptionscreen.DescriptionActivity
import ru.s1aks.translator.view.history.HistoryActivity

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override val model: MainViewModel by viewModel()
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!
                    )
                )
            }
        }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (binding.searchEditText.text != null && binding.searchEditText.text.toString()
                    .isNotEmpty()
            ) {
                if (isNetworkAvailable) {
                    model.getData(binding.searchEditText.text.toString(), true)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        model.subscribe().observe(this, { renderData(it) })
    }

    private fun initViews() {
        binding.searchEditText.addTextChangedListener(textWatcher)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.adapter = adapter
    }

    override fun setDataToView(data: List<DataModel>) {
        adapter.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
