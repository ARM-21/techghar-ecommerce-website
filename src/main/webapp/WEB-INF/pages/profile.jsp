<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary-color: #4a90e2;
            --secondary-color: #7ed6df;
            --text-dark: #2c3e50;
            --text-light: #95a5a6;
        }
        
        .content{
        display:flex;
        justify-content:center;}

        .profile-container {
            max-width: 900px;
            width: 90%;
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 8px 32px rgba(31, 38, 135, 0.15);
            display: flex;
            gap: 40px;
            transition: transform 0.3s ease;
            position: relative;
            overflow: hidden;
        }

        .profile-container::before {
            content: '';
            position: absolute;
            top: -50%;
            left: -50%;
            width: 200%;
            height: 200%;
            background: linear-gradient(45deg, var(--primary-color), var(--secondary-color));
            animation: rotate 20s linear infinite;
            opacity: 0.1;
        }

        @keyframes rotate {
            100% { transform: rotate(360deg); }
        }

        .profile-image {
            flex: 1;
            position: relative;
            z-index: 1;
            align-self: center;
        }

        .profile-image img {
            width: 220px;
            height: 220px;
            border-radius: 50%;
            object-fit: cover;
            border: 5px solid white;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }

        .profile-image:hover img {
            transform: scale(1.05);
        }

        .profile-info {
            flex: 2;
            position: relative;
            z-index: 1;
        }

        .profile-info h2 {
            color: var(--text-dark);
            margin: 0 0 25px 0;
            font-size: 2.2em;
            position: relative;
            display: inline-block;
        }

        .profile-info h2::after {
            content: '';
            position: absolute;
            bottom: -10px;
            left: 0;
            width: 50px;
            height: 3px;
            background: var(--primary-color);
        }

        .info-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px 30px;
            margin-bottom: 30px;
        }

        .info-item {
            display: flex;
            align-items: center;
            gap: 12px;
            padding: 12px;
            background: rgba(245, 247, 250, 0.6);
            border-radius: 8px;
            transition: all 0.3s ease;
        }

        .info-item:hover {
            background: white;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
            transform: translateY(-2px);
        }

        .label {
            font-weight: 500;
            color: var(--primary-color);
            min-width: 100px;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .label i {
            font-size: 1.1em;
            color: var(--secondary-color);
        }

        .value {
            color: var(--text-dark);
            font-weight: 400;
        }

        .edit-button {
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
            color: white;
            border: none;
            padding: 12px 30px;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1em;
            font-weight: 500;
            transition: all 0.3s ease;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            box-shadow: 0 4px 15px rgba(74, 144, 226, 0.3);
        }

        .edit-button:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(74, 144, 226, 0.4);
        }

        @media (max-width: 768px) {
            .profile-container {
                flex-direction: column;
                padding: 30px;
            }

            .profile-image {
                text-align: center;
            }

            .info-grid {
                grid-template-columns: 1fr;
            }
            .info-item{
            display:flex;
            flex-direction:column;
            }
        }
    </style>
</head>
<body>

<div class="profile-container">
    <div class="profile-image">
        <img src="https://cdn.britannica.com/89/7489-050-D0E7DF35/West-African-chimpanzee-pan-troglodytes.jpg" alt="Profile Picture">
    </div>
    <div class="profile-info">
        <h2>${userDetails.firstName} ${userDetails.lastName}</h2>
        <div class="info-grid">
            <div class="info-item">
                <span class="label"><i class="fas fa-user"></i>Username</span>
                <span class="value">${userDetails.username}</span>
            </div>
            <div class="info-item">
                <span class="label"><i class="fas fa-envelope"></i>Email</span>
                <span class="value">${userDetails.email}</span>
            </div>
            <div class="info-item">
                <span class="label"><i class="fas fa-phone"></i>Phone</span>
                <span class="value">${userDetails.phone}</span>
            </div>
            <div class="info-item">
                <span class="label"><i class="fas fa-map-marker-alt"></i>Address</span>
                <span class="value">${userDetails.address}</span>
            </div>
            <div class="info-item">
                <span class="label"><i class="fas fa-venus-mars"></i>Gender</span>
                <span class="value">${userDetails.gender}</span>
            </div>
            <div class="info-item">
                <span class="label"><i class="fas fa-birthday-cake"></i>Date of Birth</span>
                <span class="value"><fmt:formatDate value="${userDetails.dob}" pattern="MMMM d, yyyy" /></span>
            </div>
        </div>
        <form action=" ${sessionScope.role == 'admin'? 'update-admin-profile':'update-profile'} " method="get">
            <input type="hidden" name="id" value="${sessionScope.id}">
            <button type="submit" class="edit-button">
                <i class="fas fa-edit"></i>
                Edit Profile
            </button>
        </form>
    </div>
</div>


</body>
</html>