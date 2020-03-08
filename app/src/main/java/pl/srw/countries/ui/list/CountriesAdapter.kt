package pl.srw.countries.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.country_item.view.*
import pl.srw.countries.R
import pl.srw.countries.api.model.Country

class CountriesAdapter : RecyclerView.Adapter<CountryVH>() {

    var data: List<Country> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryVH(view)
    }

    override fun onBindViewHolder(holder: CountryVH, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

class CountryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(entity: Country) {
        with(itemView) {
            name.text = entity.name
            currency_code.text = entity.currencies.joinToString { it.code ?: "-" }
            phone_prefix.text = entity.phoneCodes.joinToString(prefix = "+")
            domain_list.text = entity.domains.joinToString()
        }
    }
}
