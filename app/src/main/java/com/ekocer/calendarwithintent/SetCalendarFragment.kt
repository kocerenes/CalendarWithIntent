package com.ekocer.calendarwithintent

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ekocer.calendarwithintent.databinding.FragmentSetCalendarBinding
import java.text.SimpleDateFormat

class SetCalendarFragment : Fragment() {

    private var _binding: FragmentSetCalendarBinding? = null
    private val binding get() = _binding!!

    // Event start and end time with date
    private val startTime = "2023-03-02T09:00:00"
    private val endTime = "2023-03-02T11:00:00"

    // Parsing the date and time
    private val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    private val mStartTime = mSimpleDateFormat.parse(startTime)
    private val mEndTime = mSimpleDateFormat.parse(endTime)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetCalendarBinding.inflate(inflater, container, false)

        binding.setCalendarButton.setOnClickListener {
            setCalendarEvent(
                "Deneme2",
                "Kocaeli",
                "Açıklama",
                mStartTime.time,
                mEndTime.time,
                false,
                "eneskocer161@gmail.com"
            )
        }

        return binding.root
    }

    private fun setCalendarEvent(
        title: String,
        location: String,
        description: String,
        begin: Long,
        end: Long,
        isAllDay: Boolean,
        email: String
    ) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.Events.EVENT_LOCATION, location)
            putExtra(CalendarContract.ExtendedProperties.DESCRIPTION, description)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
            putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, isAllDay)
            putExtra(Intent.EXTRA_EMAIL, email)
        }

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }
}