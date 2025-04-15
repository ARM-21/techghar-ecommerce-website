<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TechGhar - Register</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css" />
</head>
<body>


	<!-- Registration Section -->
	<section class="register-section">
	<div class="container">
		<div class="register-container">
			<div class="register-form-container">
				<!-- Success Message -->
				
					<!-- Success Case -->
					<c:if test="${not empty success}">
						<div class="success-message" id="successMessage">
						<span class="close-btn" onclick="this.parentElement.remove();">&times;</span>
							<span class="icon icon-check-circle"></span>
							<h3>Account Created Successfully!</h3>
							<h2>${success}</h2>
							<p>Thank you for registering with TechGhar. You can now login
								to your account.</p>
							<a href="login" class="register-button margin-top">Go to
								Login</a>
						</div>
					</c:if>

					<!-- Error Case -->
					<c:if test="${not empty error}">
						<div class="error-message" id="errorMessage">
						<span class="close-btn" onclick="this.parentElement.remove();">&times;</span>
							<span class="icon icon-error-circle"></span>
							<h3>${error}</h3>
							<p>Please try again or contact support if the problem
								persists.</p>
						</div>
					</c:if>

					<!-- Default: Show the Registration Form -->
					
						<div id="registrationForm">
							<div class="register-header">
								<h2>Create Account</h2>
								<p>Join TechGhar to explore our products</p>
							</div>
							<form method="POST" action="${pageContext.request.contextPath}/register">
								<!--   <div class="form-group">
                                <label for="firstName" class="form-label">First Name</label>
                                <input type="text" class="form-control" id="firstName"  name= "firstName" placeholder="Enter your first name" required>
                                <div class="invalid-feedback">Please enter your First name</div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="form-label">Last Name</label>
                                <input type="text" class="form-control" id="lastName" name= "lastName" placeholder="Enter your last name" required>
                                <div class="invalid-feedback">Please enter your Last name</div>
                            </div>
                            -->
								<div class="form-group">
									<label for="email" class="form-label">Email Address</label> <input
										type="email" class="form-control" id="email" name="email"
										placeholder="Enter your email" required>
									<div class="invalid-feedback">Please enter a valid email
										address</div>
								</div>
								<div class="form-group">
									<label for="username" class="form-label">Username</label> <input
										type="text" class="form-control" id="username"
										placeholder="Choose a username" name="userName" required>
									<div class="invalid-feedback">Please choose a username</div>
								</div>
								<div class="form-group">
									<label for="password" class="form-label">Password</label>
									<div class="input-group">
										<input type="password" class="form-control" id="password"
											name="password" placeholder="Create a password" required>
										<span class="input-group-text"
											onclick="togglePassword('password', 'togglePassword')">
											<span class="icon icon-eye" id="togglePassword"></span>
										</span>
									</div>
									<div class="invalid-feedback">Password doesn't meet
										requirements</div>
									<div class="password-strength">
										<div class="password-strength-meter"
											id="passwordStrengthMeter"></div>
									</div>
									<div class="strength-text" id="strengthText"></div>
									<div class="password-requestuirements">
										Password must contain:
										<ul>
											<li id="length">At least 8 characters</li>
											<li id="uppercase">At least one uppercase letter</li>
											<li id="number">At least one number</li>
											<li id="special">At least one special character</li>
										</ul>
									</div>
								</div>
								<div class="form-group">
									<label for="confirmPassword" class="form-label">Confirm
										Password</label>
									<div class="input-group">
										<input type="password" class="form-control"
											id="confirmPassword" name="confirmPassword"
											placeholder="Confirm your password" required> <span
											class="input-group-text"
											onclick="togglePassword('confirmPassword', 'toggleConfirmPassword')">
											<span class="icon icon-eye" id="toggleConfirmPassword"></span>
										</span>
									</div>
									<div class="invalid-feedback">Passwords do not match</div>
								</div>
								<!-- 
                            <div class="form-group">
                                <label for="phone" class="form-label">Phone Number</label>
                                <input type="tel" class="form-control" id="phone" placeholder="Enter your phone number"  name= "phoneNumber" required>
                                <div class="invalid-feedback">Please enter a valid phone number</div>
                            </div>
                            <div class="form-group">
                                <label for="address" class="form-label">Address</label>
                                <input type="text" class="form-control" id="address" placeholder="Enter your address" name="address" required>
                                <div class="invalid-feedback">Please enter your address</div>
                            </div>
                            <div class="form-group">
                                <label for="dob" class="form-label">Date of Birth</label>
                                <input type="date" class="form-control" id="dob"name= "dob" required>
                                <div class="invalid-feedback">Please enter your date of birth</div>
                            </div>
                            <div class="form-group">
                                <label for="gender" class="form-label">Gender</label>
                                <select class="form-control" id="gender" name= "gender" required>
                                    <option value="">Select your gender</option>
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                    <option value="other">Other</option>
                                </select>
                                <div class="invalid-feedback">Please select your gender</div>
                            </div>
                             -->

								<div class="terms-checkbox">
									<input class="checkbox-input" type="checkbox" id="termsCheck"
										requestuired> <label for="termsCheck"> I agree
										to the <a href="#" class="terms-link">Terms of Service</a> and
										<a href="#" class="terms-link">Privacy Policy</a>
									</label>
									<div class="invalid-feedback">You must agree to the terms
										and conditions</div>
								</div>
								<input type="submit" class="register-button"
									value="Create Account" />
							</form>
							<div class="login-link">
								<p>
									Already have an account? <a href="login">Login</a>
								</p>
								
							</div>
						</div>
			

			
			</div>
		</div>
	</section>

	<!-- Form Validation and Password Scripts -->
	<script>
		// Toggle password visibility
		function togglePassword(inputId, toggleId) {
			const passwordInput = document.getElementById(inputId);
			const toggleIcon = document.getElementById(toggleId);

			if (passwordInput.type === 'password') {
				passwordInput.type = 'text';
				toggleIcon.classList.remove('icon-eye');
				toggleIcon.classList.add('icon-eye-slash');
			} else {
				passwordInput.type = 'password';
				toggleIcon.classList.remove('icon-eye-slash');
				toggleIcon.classList.add('icon-eye');
			}
		}

		// Password strength checker
		/** function checkPasswordStrength(password) {
		     let strength = 0;
		     const strengthMeter = document.getElementById('passwordStrengthMeter');
		     const strengthText = document.getElementById('strengthText');
		     
		     // Update requestuirement indicators
		     document.getElementById('length').style.color = password.length >= 8 ? '#28a745' : '';
		     document.getElementById('uppercase').style.color = /[A-Z]/.test(password) ? '#28a745' : '';
		     document.getElementById('number').style.color = /[0-9]/.test(password) ? '#28a745' : '';
		     document.getElementById('special').style.color = /[^A-Za-z0-9]/.test(password) ? '#28a745' : '';
		     
		     // Calculate strength
		     if (password.length >= 8) strength += 25;
		     if (/[A-Z]/.test(password)) strength += 25;
		     if (/[0-9]/.test(password)) strength += 25;
		     if (/[^A-Za-z0-9]/.test(password)) strength += 25;
		     
		     // Update strength meter
		     strengthMeter.style.width = strength + '%';
		     
		     // Set color based on strength
		     if (strength <= 25) {
		         strengthMeter.style.backgroundColor = '#dc3545'; // Red
		         strengthText.textContent = 'Weak';
		         strengthText.style.color = '#dc3545';
		     } else if (strength <= 50) {
		         strengthMeter.style.backgroundColor = '#ffc107'; // Yellow
		         strengthText.textContent = 'Fair';
		         strengthText.style.color = '#ffc107';
		     } else if (strength <= 75) {
		         strengthMeter.style.backgroundColor = '#17a2b8'; // Blue
		         strengthText.textContent = 'Good';
		         strengthText.style.color = '#17a2b8';
		     } else {
		         strengthMeter.style.backgroundColor = '#28a745'; // Green
		         strengthText.textContent = 'Strong';
		         strengthText.style.color = '#28a745';
		     }
		     
		     return strength === 100;
		 }

		 // Check if passwords match
		 function checkPasswordsMatch() {
		     const password = document.getElementById('password').value;
		     const confirmPassword = document.getElementById('confirmPassword').value;
		     const confirmInput = document.getElementById('confirmPassword');
		     
		     if (confirmPassword === '') {
		         return false;
		     }
		     
		     if (password !== confirmPassword) {
		         confirmInput.classList.add('is-invalid');
		         return false;
		     } else {
		         confirmInput.classList.remove('is-invalid');
		         return true;
		     }
		 }

		 // Validate email format
		 function validateEmail(email) {
		     const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		     return re.test(String(email).toLowerCase());
		 }

		 // Form validation
		 document.addEventListener('DOMContentLoaded', function() {
		     const form = document.getElementById('signupForm');
		     const passwordInput = document.getElementById('password');
		     const confirmPasswordInput = document.getElementById('confirmPassword');
		     const emailInput = document.getElementById('email');
		     
		     // Password strength check on input
		     passwordInput.addEventListener('input', function() {
		         checkPasswordStrength(this.value);
		     });
		     
		     // Check passwords match on confirm password input
		     confirmPasswordInput.addEventListener('input', function() {
		         checkPasswordsMatch();
		     });
		     
		     // Email validation on blur
		     emailInput.addEventListener('blur', function() {
		         if (this.value && !validateEmail(this.value)) {
		             this.classList.add('is-invalid');
		         } else {
		             this.classList.remove('is-invalid');
		         }
		     });
		     
		     // Form submission
		     form.addEventListener('submit', function(event) {
		         event.preventDefault();
		         let isValid = true;
		         
		         // Validate full name
		         const firstName = document.getElementById('firstName');
		         if (!firstName.value.trim()) {
		             firstName.classList.add('is-invalid');
		             isValid = false;
		         } else {
		             firstName.classList.remove('is-invalid');
		         }
		         const lastName = document.getElementById('lastName');
		         if (!lastName.value.trim()) {
		             lastName.classList.add('is-invalid');
		             isValid = false;
		         } else {
		             lastName.classList.remove('is-invalid');
		         }
		         
		         // Validate email
		         if (!emailInput.value.trim() || !validateEmail(emailInput.value)) {
		             emailInput.classList.add('is-invalid');
		             isValid = false;
		         } else {
		             emailInput.classList.remove('is-invalid');
		         }
		         
		         // Validate password
		         const passwordStrong = checkPasswordStrength(passwordInput.value);
		         if (!passwordStrong) {
		             passwordInput.classList.add('is-invalid');
		             isValid = false;
		         } else {
		             passwordInput.classList.remove('is-invalid');
		         }
		         
		         // Validate passwords match
		         const passwordsMatch = checkPasswordsMatch();
		         if (!passwordsMatch) {
		             isValid = false;
		         }
		         
		         // Validate terms checkbox
		         const termsCheck = document.getElementById('termsCheck');
		         if (!termsCheck.checked) {
		             termsCheck.nextElementSibling.nextElementSibling.style.display = 'block';
		             isValid = false;
		         } else {
		             termsCheck.nextElementSibling.nextElementSibling.style.display = 'none';
		         }
		         
		         // If form is valid, show success message
		         if (isValid) {
		             document.getElementById('registrationForm').style.display = 'none';
		             document.getElementById('successMessage').style.display = 'block';
		             
		             // You would typically send the form data to your server here
		             console.log('Form submitted successfully');

		         }
		     });
		 });
		 **/
	</script>
</body>
</html>
