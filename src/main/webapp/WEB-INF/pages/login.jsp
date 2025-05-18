<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechGhar - Login</title>
   <!-- <link rel="stylesheet" href="login.css">
   
     -->
   <style>
    
:root {
    --primary-color: #6366F1;
    --primary-hover: #4F46E5;
    --text-color: #333;
    --light-gray: #f8f9fa;
    --border-color: #e0e0e0;
}

body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    color: var(--text-color);
    background-color: #fff;
    margin: 0;
    padding: 0;
}

/* Navbar Styling */
.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 20px;
    background-color: white;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
}

.navbar-brand img {
    height: 40px;
}

.navbar-nav {
    display: flex;
    gap: 15px;
}

.nav-link {
    color: var(--text-color);
    font-weight: 500;
    text-decoration: none;
    padding: 8px 15px;
    border-radius: 5px;
    transition: all 0.3s ease;
}

.nav-link:hover {
    color: var(--primary-color);
    background-color: rgba(99, 102, 241, 0.05);
}

.nav-icons {
    display: flex;
    align-items: center;
    gap: 20px;
}

.nav-icons a {
    color: var(--text-color);
    font-size: 18px;
    text-decoration: none;
    transition: color 0.3s ease;
}

.nav-icons a:hover {
    color: var(--primary-color);
}

/* Login Section */
.login-section {
    padding: 80px 20px;
    background-color: var(--light-gray);
    display: flex;
    justify-content: center;
    align-items: center;
}

.login-container {
    max-width: 550px;
    width: 100%;
    background: white;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
    padding: 50px;
}

.login-header {
    text-align: center;
    margin-bottom: 30px;
}

.login-header h2 {
    font-weight: 700;
    color: var(--text-color);
    margin-bottom: 10px;
}

.login-header p {
    color: #777;
}

.form-group {
    margin-bottom: 25px;
}

.form-label {
    font-weight: 500;
    margin-bottom: 8px;
    display: block;
}

.form-control {
    width: 100%;
    height: 50px;
    border: 1px solid var(--border-color);
    border-radius: 8px;
    padding: 10px 15px;
    font-size: 15px;
    transition: all 0.3s;
}

.form-control:focus {
    border-color: var(--primary-color);
    box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.2);
    outline: none;
}

.input-group {
    display: flex;
    align-items: center;
}

.input-group-text {
    background-color: white;
    border: 1px solid var(--border-color);
    border-left: none;
    cursor: pointer;
    padding: 0 15px;
}

.input-group-text:hover {
    color: var(--primary-color);
}

.remember-forgot {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 25px;
}

.form-check-input {
    margin-right: 10px;
}

.forgot-password {
    color: var(--primary-color);
    text-decoration: none;
    font-weight: 500;
    transition: color 0.3s;
}

.forgot-password:hover {
    color: var(--primary-hover);
    text-decoration: underline;
}

.login-button {
    display: block;
    width: 100%;
    background-color: var(--primary-color);
    color: white;
    border: none;
    border-radius: 8px;
    padding: 15px;
    font-size: 16px;
    font-weight: 600;
    cursor: pointer;
    transition: background-color 0.3s;
}

.login-button:hover {
    background-color: var(--primary-hover);
}

.social-login {
    margin-top: 30px;
    text-align: center;
}

.social-login-title {
    position: relative;
    margin-bottom: 20px;
    color: #777;
}

.social-login-title::before,
.social-login-title::after {
    content: "";
    position: absolute;
    top: 50%;
    width: 30%;
    height: 1px;
    background-color: var(--border-color);
}

.social-login-title::before {
    left: 0;
}

.social-login-title::after {
    right: 0;
}

.social-buttons {
    display: flex;
    justify-content: center;
    gap: 15px;
    margin-top: 20px;
}

.social-button {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    background-color: white;
    border: 1px solid var(--border-color);
    color: #555;
    transition: all 0.3s;
}

.social-button:hover {
    transform: translateY(-3px);
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
}

.register-link {
    text-align: center;
    margin-top: 30px;
}

.register-link a {
    color: var(--primary-color);
    font-weight: 600;
    text-decoration: none;
    transition: color 0.3s;
}

.register-link a:hover {
    color: var(--primary-hover);
    text-decoration: underline;
}
.alert {
  position: relative;
  padding: 1rem 1.5rem;
  margin-bottom: 1rem;
  border: 1px solid transparent;
  border-radius: 0.5rem;
  font-size: 1rem;
  transition: all 0.3s ease-in-out;
}

.alert-danger {
  color: #842029;
  background-color: #f8d7da;
  border-color: #f5c2c7;
}

.alert-dismissible .btn-close {
  position: absolute;
  top: 0.5rem;
  right: 1rem;
  background: none;
  border: none;
  font-size: 1.2rem;
  color: #842029;
  cursor: pointer;
}

.btn-close:hover {
  color: #660d1a;
}

.alert.fade {
  opacity: 0;
  transition: opacity 0.15s linear;
}

.alert.fade.show {
  opacity: 1;
}



/* Responsive Styles */
@media (max-width: 768px) {
    .login-container {
        padding: 30px;
    }

    .login-header h2 {
        font-size: 24px;
    }

    .form-control {
        height: 45px;
        font-size: 14px;
    }

    .login-button {
        padding: 12px;
        font-size: 14px;
    }

    .social-buttons {
        gap: 10px;
    }
}

@media (max-width: 576px) {
    .login-section {
        padding: 40px 20px;
    }

    .remember-forgot {
        flex-direction: column;
        align-items: flex-start;
        gap: 10px;
    }

    .navbar {
        flex-direction: column;
        align-items: flex-start;
        gap: 10px;
    }

    .navbar-nav {
        flex-direction: column;
        gap: 10px;
    }

    .nav-icons {
        gap: 10px;
    }
}

   </style>
</head>
<body>
 

    <!-- Login Section -->
    <section class="login-section">
        <div class="login-container">
            <div class="login-header">
                <h2>Welcome Back</h2>
                <p>Please login to your account</p>
            </div>
           
           		<!-- Error Message Display -->
			<c:if test="${not empty message}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					${message}
					<button type="button" class="btn-close" 
						>&times;</button>
				</div>
			</c:if>
            <form action="/login" method="POST">
                <div class="form-group">
                    <label for="email" class="form-label">Email Address</label>
                   <div class="input-group" >
                    <input type="email" class="form-control" id="email" placeholder="Enter your email" name="email" required>
                	</div>
                </div>
                <div class="form-group">
                    <label for="password" class="form-label">Password</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="password" placeholder="Enter your password" name="password" required>
                        <span class="input-group-text" onclick="togglePassword()">👁️</span>
                    </div>
                </div>
                <div class="remember-forgot">
                    <label>
                        <input class="form-check-input" type="checkbox" id="rememberMe" name="rememberMe" >
                        Remember me
                    </label>
                    
                </div>
                <button type="submit" class="login-button">Login</button>
         </form>
            <div class="register-link">
                <p>Don't have an account? <a href="register">Register Now</a></p>
            </div>
        </div>
        
    </section>

  

    <!-- Password Toggle Script -->
    <script>
        function togglePassword() {
            const passwordInput = document.getElementById('password');
            const toggleIcon = document.querySelector('.input-group-text');
            
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                toggleIcon.textContent = '🙈';
            } else {
                passwordInput.type = 'password';
                toggleIcon.textContent = '👁️';
            }
        }
        
        document.querySelectorAll('.btn-close').forEach(button => {
            button.addEventListener('click', function () {
              const alert = this.closest('.alert');
              if (alert) {
                alert.classList.remove('show');
                setTimeout(() => alert.remove(), 150); // Optional smooth removal
              }
            });
          });
    </script>
</body>
</html>