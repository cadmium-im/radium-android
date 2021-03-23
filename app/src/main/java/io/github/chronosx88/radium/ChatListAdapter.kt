package io.github.chronosx88.radium

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import io.github.chronosx88.radium.models.Chat
import io.github.chronosx88.radium.utils.time.LocaleUtils
import java.util.ArrayList

class ChatListAdapter(val context: Context) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {
    val data = ArrayList<Chat>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chatName: TextView = view.findViewById(R.id.chat_list_item_name)
        val avatar: CircleImageView = view.findViewById(R.id.chat_list_item_avatar)
        val lastMsg: TextView = view.findViewById(R.id.chat_list_item_last_msg)
        val lastMessageReadState: ImageView = view.findViewById(R.id.chat_list_read_icon)
        val lastMessageTime: TextView = view.findViewById(R.id.chat_list_last_msg_timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_list_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.chatName.text = data[position].name
        holder.lastMsg.text = data[position].lastMsg.text

        if (data[position].lastMsg.author) {
            val doneIcon: Drawable? = AppCompatResources.getDrawable(context, R.drawable.ic_done_purple_24)
            val doneAllIcon: Drawable? = AppCompatResources.getDrawable(context, R.drawable.ic_done_all_purple_24)
            holder.lastMessageReadState.setImageDrawable(if (data[position].lastMsg.read) doneAllIcon!! else doneIcon!!)
        } else {
            holder.lastMessageReadState.visibility = View.INVISIBLE
        }


        Glide.with(holder.itemView)
                .load(data[position].photoURL)
                .into(holder.avatar)

        val lastMessageDateText = LocaleUtils.stringForMessageListDate(data[position].lastMsg.timestamp)
        holder.lastMessageTime.text = lastMessageDateText
    }

    override fun getItemCount(): Int = data.size
}