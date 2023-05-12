package com.sum.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author mingyan.su
 * @date   2023/3/19 18:12
 * @desc   分类tab信息
 */
data class CategoryItem(
    val cid: Int?,
    val name: String?,
    var isSelected: Boolean? = false,
    val articles: MutableList<CategorySecondItem>? = mutableListOf()
)

@Parcelize
data class CategorySecondItem(
    val id: Int?, val link: String?, val title: String?, val chapterId: Int? = 0
) : Parcelable {
    var type = ""
}