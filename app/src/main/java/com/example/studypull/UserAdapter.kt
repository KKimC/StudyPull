package com.example.studypull

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studypull.User

class UserAdapter(private val context: android.content.Context, private val userList:ArrayList<User>):
RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):UserViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)

        return UserViewHolder(view)
    }
    //데이터 설정
    override fun onBindViewHolder(holder:UserViewHolder, position : Int) {
        //데이터 담기
        val currentUser = userList[position]

        //화면에 데이터 보여주기
        holder.nameText.text = currentUser.name

        //이이템 클릭 이벤트
        holder.itemView.setOnClickListener{
            var intent = Intent(context,ChatActivity::class.java)

            intent.putExtra("name",currentUser.name)
            intent.putExtra("uId",currentUser.uId)

        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.name_text)
    }
}