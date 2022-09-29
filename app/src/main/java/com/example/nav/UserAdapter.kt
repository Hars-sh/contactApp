package com.example.nav

import android.view.ViewGroup
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_profile.view.*
import java.util.ArrayList

class UserAdapter(val context: Context,val userList: ArrayList<user_data>): RecyclerView.Adapter<UserAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView=LayoutInflater.from(context).inflate(R.layout.user_profile,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.name.text = userList[position].name
        holder.mobile.text = userList[position].number
        holder.email.text = userList[position].email

        Glide.with(context).load(userList[position].image_url).into(holder.image)
        var n: String? = null
        holder.btn.setOnClickListener{
            n=holder.mobile.text.toString().trim()
            val intent=Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+Uri.encode(n)))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
            var name = itemView.image_name
            var email = itemView.image_email
            var mobile = itemView.image_number
            var image = itemView.pic
        var btn=itemView.caller_button
        }
    }

/*class UserAdapter:RecyclerView.Adapter<UserAdapter.MyViewHolder>()
{ private lateinit var view:View
    lateinit var bt1:ImageButton
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        view=inflater.inflate(R.layout.user_profile,parent,false)
        return MyViewHolder(view)
    }
    private val name= arrayOf("Sneha Aggarwal","Ankit kumar","HARSH JALAN","SHREYA Bajpai","Virat Kholi","Bharat JALAN")
    val number = arrayOf("9044875811","8787230761","951996894","051224620","904587892","25647845")
    private val email= arrayOf("Sneha123@gmail.com",
        "Ankit1232@gmail.com","hjalan123@gmail.com","Sherya123@gmail.com", "virat001@gmail.com","bjalan123@gmail.com")
    private val images= intArrayOf(R.drawable.men,R.drawable.images,R.drawable.tom,R.drawable.jerry,R.drawable.xyz,R.drawable.women)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = "Name: ".plus(name[position])
        holder.email.text = "Email-id: ".plus(email[position])
        holder.mobile.text = "Mobile_NO: ".plus(number[position])
        holder.image.setImageResource(images[position])
    }
    override fun getItemCount(): Int {
        return images.size
    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.image_name)
        var email = itemView.findViewById<TextView>(R.id.image_email)
        var mobile = itemView.findViewById<TextView>(R.id.image_number)
        var image = itemView.findViewById<ImageView>(R.id.pic)
    }

}
*/
