package com.truongdinh.waiterapp.signin

import com.truongdinh.waiterapp.MainDispatcherRule
import com.truongdinh.waiterapp.data.local.session.SessionManager
import com.truongdinh.waiterapp.data.repository.StaffRepository
import com.truongdinh.waiterapp.ui.features.auth.signin.SignInViewModel
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignInViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val staffRepository: StaffRepository = mockk(relaxed = true)
    private val sessionManager: SessionManager = mockk(relaxed = true)

    private lateinit var viewModel: SignInViewModel

    @Before
    fun setUp() {
        viewModel = SignInViewModel(staffRepository, sessionManager)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `blank username should show error`() = runTest {
        viewModel.onPasswordChanged("123456")

        viewModel.signIn {  }

        assertEquals(
            "Tên đăng nhập không được để trống",
            viewModel.uiState.usernameError
        )
    }

    @Test
    fun `blank password should show error`() = runTest {
        viewModel.onUsernameChanged("waiter01")

        viewModel.signIn {  }

        assertEquals(
            "Mật khẩu không được để trống",
            viewModel.uiState.passwordError
        )
    }

    @Test
    fun `password must be at least 6 characters`() = runTest {
        viewModel.onUsernameChanged("waiter01")
        viewModel.onPasswordChanged("12345")
        viewModel.signIn {}
        assertEquals(
            "Mật khẩu phải có ít nhất 6 ký tự",
            viewModel.uiState.passwordError
        )
    }
}