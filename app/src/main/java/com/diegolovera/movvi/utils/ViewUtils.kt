package com.diegolovera.movvi.utils

import android.content.Context
import com.diegolovera.movvi.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

object ViewUtils {
    fun createChip(text: String, context: Context): Chip {
        val chip = Chip(context)
        chip.text = text
        val shape = ShapeAppearanceModel()
        shape.setAllCorners(CornerFamily.ROUNDED, 18)
        val chipDrawable = ChipDrawable.createFromResource(context, R.xml.chip_style)
        chipDrawable.shapeAppearanceModel = shape
        chip.setChipDrawable(chipDrawable)
        chip.isClickable = false
        chip.isCheckable = false
        chip.isCloseIconVisible = false
        return chip
    }

    fun createChip(text: String, selectable: Boolean, context: Context): Chip {
        val chip = Chip(context)
        chip.text = text
        val shape = ShapeAppearanceModel()
        shape.setAllCorners(CornerFamily.ROUNDED, 18)
        val chipDrawable = ChipDrawable.createFromResource(context, R.xml.filter_chip_style)
        chipDrawable.shapeAppearanceModel = shape
        chip.setChipDrawable(chipDrawable)
        chip.isClickable = selectable
        chip.isCheckable = selectable
        chip.isCloseIconVisible = false
        return chip
    }
}