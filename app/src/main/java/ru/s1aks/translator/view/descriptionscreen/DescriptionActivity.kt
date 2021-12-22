package ru.s1aks.translator.view.descriptionscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope
import ru.s1aks.translator.R
import ru.s1aks.translator.databinding.ActivityDescriptionBinding
import ru.s1aks.translator.model.data.AppState
import ru.s1aks.translator.model.data.DataModel
import ru.s1aks.translator.utils.convertMeaningsToString
import ru.s1aks.translator.utils.network.OnlineLiveData
import ru.s1aks.translator.view.base.BaseActivity

class DescriptionActivity : BaseActivity<AppState, DescriptionInteractor>(), AndroidScopeComponent {

    override val scope: Scope by activityScope()
    override val model: DescriptionViewModel by scope.inject()
    private lateinit var binding: ActivityDescriptionBinding
    private var searchWord: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    override fun onResume() {
        super.onResume()
        model.subscribe().observe(this, { renderData(it) })
    }

    private fun initView() {
        setActionbarHomeButtonAsUp()
        binding.descriptionScreenSwipeRefreshLayout.setOnRefreshListener { startLoadingOrShowError() }
        val bundle = intent.extras
        searchWord = bundle?.getString(WORD_EXTRA)
        binding.descriptionHeader.text = searchWord
        startLoadingOrShowError()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setDataToView(data: List<DataModel>) {
        val dataEntity = data.filter { it.text == searchWord }
        binding.descriptionTextview.text = convertMeaningsToString(dataEntity[0].meanings!!)
        val imageLink = dataEntity[0].meanings?.get(0)?.imageUrl
        if (imageLink.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {
            useCoilToLoadPhoto(binding.descriptionImageview, imageLink)
        }
    }

    private fun startLoadingOrShowError() {
        OnlineLiveData(this).observe(
            this@DescriptionActivity,
            {
                if (it) {
                    searchWord?.let { word -> model.getData(word, true) }
                } else {
                    showNoInternetConnectionDialog()
                    stopRefreshAnimationIfNeeded()
                }
            })
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (binding.descriptionScreenSwipeRefreshLayout.isRefreshing) {
            binding.descriptionScreenSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun useCoilToLoadPhoto(imageView: ImageView, imageLink: String) {
        imageView.load("https:$imageLink") {
            crossfade(true)
            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.ic_no_photo_vector)
            transformations(RoundedCornersTransformation(5.0F))
        }
    }

    companion object {
        private const val WORD_EXTRA = "key_word"

        fun getIntent(
            context: Context,
            word: String
        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(WORD_EXTRA, word)
        }
    }
}
