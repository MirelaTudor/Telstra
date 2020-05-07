package au.com.telstra.ui.fact

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import au.com.telstra.R

/**
 * The view that represents one item of the list
 */
class FactView @JvmOverloads constructor(
    context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attributes, defStyleAttr) {

    data class DataModel(val title: String?,
                    val description: String?,
                    val imageUrl: String?)

    init {
        inflate(context, R.layout.item_fact, this)

        layoutParams = MarginLayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setPadding(resources.getDimensionPixelOffset(R.dimen.normal_margin))
    }

    fun bindData(fact: DataModel) {

    }
}
