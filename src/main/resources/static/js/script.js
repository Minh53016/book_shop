function toggleLanguage() {
    let language = document.getElementById("top-language-dropdown");
    if (language.style.display === "block") {
        language.setAttribute("style", "display: none;");
    } else {
        language.setAttribute("style", "display: block;");
    }
}



var checkToggleLanguage = function (event) {
    let find = false;
    let element = event.target;
    for (let i = 0; i < element.classList.length; i++) {
        if (element.classList[i].includes("top-language")) {
            find = true;
            break;
        }
    }


    if (find) {

    } else {
        let language = document.getElementById("top-language-dropdown");
        language.setAttribute("style", "display: none;");
    }

}

var allElement = document.getElementsByTagName("*");
for (let i = 0; i < allElement.length; i++) {
    allElement[i].addEventListener("click", checkToggleLanguage);
}




//Time Down
function updateCountdown() {
    const countdownElement = document.querySelector('.flashsale-header-countdown');
    const hoursElement = countdownElement.querySelector('#hours');
    const minutesElement = countdownElement.querySelector('#minutes');
    const secondsElement = countdownElement.querySelector('#seconds');

    let hours = parseInt(hoursElement.textContent);
    let minutes = parseInt(minutesElement.textContent);
    let seconds = parseInt(secondsElement.textContent);

    if (seconds > 0) {
        seconds--;
    } else {
        seconds = 59;

        if (minutes > 0) {
            minutes--;
        } else {
            minutes = 59;

            if (hours > 0) {
                hours--;
            }
        }
    }

    hoursElement.textContent = hours.toString().padStart(2, '0');
    minutesElement.textContent = minutes.toString().padStart(2, '0');
    secondsElement.textContent = seconds.toString().padStart(2, '0');
}

// Update countdown every second
setInterval(updateCountdown, 1000);

// Khi nhấn vào icon thông báo
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = 'none';
    }
};

// JavaScript để điều khiển banner
function closeBanner() {
    var banner = document.querySelector('.banner-container');
    banner.style.display = 'none'; // Đóng banner khi nút close được nhấn
}

// Hiển thị banner sau khi đăng nhập (đặt mã này ở nơi thích hợp sau khi đăng nhập)
var banner = document.querySelector('.banner-container');
banner.style.display = 'block'; // Hiển thị banner khi cần
