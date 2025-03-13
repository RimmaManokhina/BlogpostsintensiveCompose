package ru.easycode.blogpostsintensive.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {

    fun showScreen(container: Int, supportFragmentManager: FragmentManager) = Unit

    abstract class Abstract(private val clazz: Class<out Fragment>) : Screen {

        protected open fun fragment(): Fragment =
            clazz.getDeclaredConstructor().newInstance()
    }

    abstract class Add(private val clazz: Class<out Fragment>) : Abstract(clazz) {

        override fun showScreen(container: Int, supportFragmentManager: FragmentManager) {
            if (supportFragmentManager.findFragmentByTag(clazz.simpleName) == null)
                supportFragmentManager.beginTransaction()
                    .add(container, fragment(), clazz.simpleName)
                    .addToBackStack(clazz.simpleName)
                    .commit()
        }
    }

    abstract class Replace(private val clazz: Class<out Fragment>) : Abstract(clazz) {

        override fun showScreen(container: Int, supportFragmentManager: FragmentManager) {
            if (supportFragmentManager.findFragmentByTag(clazz.simpleName) == null)
                supportFragmentManager.beginTransaction()
                    .replace(
                        container,
                        fragment(),
                        clazz.simpleName
                    )
                    .commit()
        }
    }

    object Empty : Screen {
        override fun showScreen(container: Int, supportFragmentManager: FragmentManager) = Unit
    }

    object Pop : Screen {
        override fun showScreen(container: Int, supportFragmentManager: FragmentManager) {
            supportFragmentManager.popBackStack()
        }
    }
}