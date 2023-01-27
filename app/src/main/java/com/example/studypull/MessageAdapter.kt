package com.example.studypull

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.grpc.Context
import com.example.studypull.navigation.model.Message
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(private val context: android.content.Context, private val messageList:ArrayList<Message>):
RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val receiver = 1
    private val send = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType==1){
            val view : View = LayoutInflater.from(context).inflate(R.layout.receiver,parent,false)
            ReceiveViewHolder(view)
        }else{
            val view : View = LayoutInflater.from(context).inflate(R.layout.send,parent,false)
            SendViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]

        if(holder.javaClass == SendViewHolder::class.java){
            val viewHolder = holder as SendViewHolder
            viewHolder.sendMessage.text = currentMessage.message
        }
        else{
            val viewHolder = holder as ReceiveViewHolder
            viewHolder.receiveMessage.text = currentMessage.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        return if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.sendId)) {
            send
        } else {
            receiver
        }
    }
    class ReceiveViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        val receiveMessage : TextView = itemView.findViewById(R.id.receiver_message_text)
    }
    class SendViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val sendMessage : TextView = itemView.findViewById(R.id.send_message_text)
    }
}