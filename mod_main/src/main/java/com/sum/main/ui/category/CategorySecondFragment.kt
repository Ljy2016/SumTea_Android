package com.sum.main.ui.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.sum.common.constant.KEY_LIST
import com.sum.common.constant.USER_ACTIVITY_SETTING
import com.sum.common.model.CategorySecondItem
import com.sum.common.provider.MainServiceProvider
import com.sum.framework.base.BaseMvvmFragment
import com.sum.framework.ext.dividerGridSpace
import com.sum.framework.ext.gone
import com.sum.framework.ext.toBeanOrNull
import com.sum.framework.ext.visible
import com.sum.framework.toast.TipsToast
import com.sum.main.R
import com.sum.main.databinding.FragmentCategorySecondBinding
import com.sum.main.ui.category.adapter.CategorySecondItemAdapter
import com.sum.main.ui.category.viewmodel.CategoryViewModel

/**
 * @author mingyan.su
 * @date   2023/3/19 22:31
 * @desc   分类item
 */
class CategorySecondFragment :
    BaseMvvmFragment<FragmentCategorySecondBinding, CategoryViewModel>() {
    private lateinit var mAdapter: CategorySecondItemAdapter

    companion object {
        fun newInstance(jsonStr: String): CategorySecondFragment {
            val fragment = CategorySecondFragment()
            val bundle = Bundle()
            bundle.putString(KEY_LIST, jsonStr)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mAdapter = CategorySecondItemAdapter()
        mBinding?.recyclerView?.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
            dividerGridSpace(2, 8.0f, true)
        }
        mAdapter.onItemClickListener = { _: View, position: Int ->
            val item = mAdapter.getItem(position)
            if (item != null && !item.link.isNullOrEmpty()) {
                if (item.type.isNotEmpty()) {
                    ARouter.getInstance().build(item.link).navigation()
                } else {
                    MainServiceProvider.toArticleDetail(
                        context = requireContext(), url = item.link!!, title = item.title ?: ""
                    )
                }
            }
        }
    }

    override fun initData() {
        val listJson = arguments?.getString(KEY_LIST, "")
        val list = listJson?.toBeanOrNull<MutableList<CategorySecondItem>>()
        mAdapter.setData(list)
        if (list.isNullOrEmpty()) {
            mBinding?.viewEmptyData?.visible()
        } else {
            mBinding?.viewEmptyData?.gone()
        }
    }
}