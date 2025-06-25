package com.hussain.weatherassessmenttest.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hussain.weatherassessmenttest.Adapter.UserAdapter
import com.hussain.weatherassessmenttest.databinding.ActivityUserListBinding
import com.hussain.weatherassessmenttest.viewmodel.UserViewModel


class UserListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserListBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupViewModel()
        setupFab()
        setupSwipeToDelete()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter { user ->
            // Navigate to weather screen when user is clicked
            val intent = Intent(this, WeatherActivity::class.java).apply {
                putExtra("user_name", "${user.firstName} ${user.lastName}")
            }
            startActivity(intent)
        }

        binding.recyclerViewUsers.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@UserListActivity)
        }
    }

    private fun setupViewModel() {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.allUsers.observe(this) { users ->
            userAdapter.submitList(users)
        }
    }

    private fun setupFab() {
        binding.fabAddUser.setOnClickListener {
            startActivity(Intent(this, UserFormActivity::class.java))
        }
    }

    private fun setupSwipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val user = userAdapter.currentList[position]
                userViewModel.deleteUser(user)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.recyclerViewUsers)
    }
}