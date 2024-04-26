<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
.logo {
	display: flex;
	position: relative;
}
.logo > img {
	width: 120px; margin-bottom: 5px; 
}
.logo > span {
	position: absolute;
	right: 0;
}
</style>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp" %>
<body>

<section class="section banner relative">
  <div class="container">
    <div class="row items-center">
      <div class="lg:col-6">
        <h1 class="banner-title">
          IT's 굿잡! 당신을 위한 맞춤형 추천을 준비했어요.
        </h1>
        <p class="mt-6">
          당신의 성향을 분석하고 니즈와 선호도에 딱 맞는 맞춤형 기업 추천을 제공합니다. 함께하는 과정에서 당신이 빛을 발할 수 있도록 도와드릴게요.
        </p>
        <a class="btn btn-white mt-8" href="#">굿잡forU 이동하기</a>
      </div>
      <div class="lg:col-6">
        <img
          class="w-full object-contain"
          src="/good/assets/images/banner-img.png"
          width="603"
          height="396"
          alt=""
        />
      </div>
    </div>
  </div>
</section>
<!-- ./end Banner -->

<!-- Key features -->
<section class="section key-feature relative">
  <div class="container">
    <div
      class="key-feature-grid mt-10 grid grid-cols-2 gap-7 md:grid-cols-3 xl:grid-cols-4"
    >
      <div
        class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg"
      >
      <div class="logo">
          <img
            class=""
            src="/good/assets/images/logo/default.jpg"
            alt=""
          />
          <span class="scrap">스크랩수</span>
        </div>
        <div>
          <h3 class="h4 text-xl lg:text-2xl">회사명</h3>
        </div>
        
      </div>
      <div
        class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg"
      >
        <div>
          <h3 class="h4 text-xl lg:text-2xl">Smart Reply</h3>
          <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
        </div>
        <span class="icon mt-4">
          <img
            class="objec-contain"
            src="/good/asset/images/icons/feature-icon-2.svg"
            alt=""
          />
        </span>
      </div>
      <div
        class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg"
      >
        <div>
          <h3 class="h4 text-xl lg:text-2xl">Sound Amplifier</h3>
          <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
        </div>
        <span class="icon mt-4">
          <img
            class="objec-contain"
            src="/good/asset/images/icons/feature-icon-3.svg"
            alt=""
          />
        </span>
      </div>
      <div
        class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg"
      >
        <div>
          <h3 class="h4 text-xl lg:text-2xl">Gesture Navigation</h3>
          <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
        </div>
        <span class="icon mt-4">
          <img
            class="objec-contain"
            src="images/icons/feature-icon-4.svg"
            alt=""
          />
        </span>
      </div>
      <div
        class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg"
      >
        <div>
          <h3 class="h4 text-xl lg:text-2xl">Dark Theme</h3>
          <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
        </div>
        <span class="icon mt-4">
          <img
            class="objec-contain"
            src="images/icons/feature-icon-5.svg"
            alt=""
          />
        </span>
      </div>
      <div
        class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg"
      >
        <div>
          <h3 class="h4 text-xl lg:text-2xl">Privacy Controls</h3>
          <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
        </div>
        <span class="icon mt-4">
          <img
            class="objec-contain"
            src="images/icons/feature-icon-6.svg"
            alt=""
          />
        </span>
      </div>
      <div
        class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg"
      >
        <div>
          <h3 class="h4 text-xl lg:text-2xl">Location Controls</h3>
          <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
        </div>
        <span class="icon mt-4">
          <img
            class="objec-contain"
            src="images/icons/feature-icon-7.svg"
            alt=""
          />
        </span>
      </div>
      <div
        class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg"
      >
        <div>
          <h3 class="h4 text-xl lg:text-2xl">Security Updates</h3>
          <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
        </div>
        <span class="icon mt-4">
          <img
            class="objec-contain"
            src="images/icons/feature-icon-8.svg"
            alt=""
          />
        </span>
      </div>
      <div
        class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg"
      >
        <div>
          <h3 class="h4 text-xl lg:text-2xl">Focus Mode</h3>
          <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
        </div>
        <span class="icon mt-4">
          <img
            class="objec-contain"
            src="images/icons/feature-icon-9.svg"
            alt=""
          />
        </span>
      </div>
      <div
        class="flex flex-col justify-between rounded-lg bg-white p-5 shadow-lg"
      >
        <div>
          <h3 class="h4 text-xl lg:text-2xl">Family Link</h3>
          <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
        </div>
        <span class="icon mt-4">
          <img
            class="objec-contain"
            src="images/icons/feature-icon-10.svg"
            alt=""
          />
        </span>
      </div>
    </div>
  </div>
</section>
<!-- ./end Key features -->

<!-- Services -->
<section class="section services">
  <div class="container">
    <div class="tab row gx-5 items-center" data-tab-group="integration-tab">
      <div class="lg:col-7 lg:order-2">
        <div class="tab-content" data-tab-content>
          <div class="tab-content-panel active" data-tab-panel="0">
            <img
              class="w-full object-contain"
              src="images/sells-by-country.png"
            />
          </div>
          <div class="tab-content-panel" data-tab-panel="1">
            <img class="w-full object-contain" src="images/collaboration.png" />
          </div>
          <div class="tab-content-panel" data-tab-panel="2">
            <img
              class="w-full object-contain"
              src="images/sells-by-country.png"
            />
          </div>
        </div>
      </div>
      <div class="mt-6 lg:col-5 lg:order-1 lg:mt-0">
        <div class="text-container">
          <h2 class="lg:text-4xl">
            Prevent failure from to impacting your reputation
          </h2>
          <p class="mt-4">
            Our platform helps you build secure onboarding authentication
            experiences that retain and engage your users. We build the
            infrastructure, you can.
          </p>
          <ul class="tab-nav -ml-4 mt-8 border-b-0" data-tab-nav>
            <li class="tab-nav-item active" data-tab="0">
              <img class="mr-3" src="images/icons/drop.svg" alt="" />
              Habit building essential choose habit
            </li>
            <li class="tab-nav-item" data-tab="1">
              <img class="mr-3" src="images/icons/brain.svg" alt="" />
              Get an overview of Habit Calendars.
            </li>
            <li class="tab-nav-item" data-tab="2">
              <img class="mr-3" src="images/icons/timer.svg" alt="" />
              Start building with Habitify platform
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="row gx-5 mt-12 items-center lg:mt-0">
      <div class="lg:col-7">
        <div class="relative">
          <img class="w-full object-contain" src="images/collaboration.png" />
        </div>
      </div>
      <div class="mt-6 lg:col-5 lg:mt-0">
        <div class="text-container">
          <h2 class="lg:text-4xl">
            Accept payments any country in this whole universe
          </h2>
          <p class="mt-4">
            Donec sollicitudin molestie malesda. Donec sollitudin molestie
            malesuada. Mauris pellentesque nec, egestas non nisi. Cras ultricies
            ligula sed
          </p>
          <ul class="mt-6 text-dark lg:-ml-4">
            <li class="mb-2 flex items-center rounded px-4">
              <img
                class="mr-3"
                src="images/icons/checkmark-circle.svg"
                alt=""
              />
              Supporting more than 119 country world
            </li>
            <li class="mb-2 flex items-center rounded px-4">
              <img
                class="mr-3"
                src="images/icons/checkmark-circle.svg"
                alt=""
              />
              Open transaction with more than currencies
            </li>
            <li class="flex items-center rounded px-4">
              <img
                class="mr-3"
                src="images/icons/checkmark-circle.svg"
                alt=""
              />
              Customer Service with 79 languages
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="row gx-5 mt-12 items-center lg:mt-0">
      <div class="lg:col-7 lg:order-2">
        <div class="video pb-5 pr-9">
          <div class="video-thumbnail overflow-hidden rounded-2xl">
            <img
              class="w-full object-contain"
              src="images/intro-thumbnail.png"
              alt=""
            />
            <button class="video-play-btn">
              <img src="images/icons/play-icon.svg" alt="" />
            </button>
          </div>
          <div
            class="video-player absolute left-0 top-0 z-10 hidden h-full w-full"
          >
            <iframe
              class="h-full w-full"
              allowfullscreen=""
              src="https://www.youtube.com/embed/g3-VxLQO7do?autoplay=1"
            ></iframe>
          </div>
        </div>
      </div>
      <div class="mt-6 lg:col-5 lg:order-1 lg:mt-0">
        <div class="text-container">
          <h2 class="lg:text-4xl">Accountability that works for you</h2>
          <p class="mt-4">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi
            egestas Werat viverra id et aliquet. vulputate egestas sollicitudin
            .
          </p>
          <button class="btn btn-white mt-6">know about us</button>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- ./end Services -->

<!-- Reviews -->
<section class="reviews">
  <div class="container">
    <div class="row justify-between">
      <div class="lg:col-6">
        <h2>Our customers have nice things to say about us</h2>
      </div>
      <div class="lg:col-4">
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi egestas
          Werat viverra id et aliquet. vulputate egestas sollicitudin .
        </p>
      </div>
    </div>
    <div class="row mt-10">
      <div class="col-12">
        <div class="swiper reviews-carousel">
          <div class="swiper-wrapper">
            <div class="swiper-slide">
              <div class="review">
                <div class="review-author-avatar bg-gradient">
                  <img src="images/users/user-5.png" alt="" />
                </div>
                <h4 class="mb-2">Courtney Henry</h4>
                <p class="mb-4 text-[#666]">microsoft corp</p>
                <p>
                  Our platform helps build secure onboarding authentica
                  experiences & engage your users. We build .
                </p>
                <div
                  class="review-rating mt-6 flex items-center justify-center space-x-2.5"
                >
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star-white.svg" alt="" />
                </div>
              </div>
            </div>
            <div class="swiper-slide">
              <div class="review">
                <div class="review-author-avatar bg-gradient">
                  <img src="images/users/user-2.png" alt="" />
                </div>
                <h4 class="mb-2">Ronald Richards</h4>
                <p class="mb-4 text-[#666]">meta limited</p>
                <p>
                  Our platform helps build secure onboarding authentica
                  experiences & engage your users. We build .
                </p>
                <div
                  class="review-rating mt-6 flex items-center justify-center space-x-2.5"
                >
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star-white.svg" alt="" />
                </div>
              </div>
            </div>
            <div class="swiper-slide">
              <div class="review">
                <div class="review-author-avatar bg-gradient">
                  <img src="images/users/user-6.png" alt="" />
                </div>
                <h4 class="mb-2">Bessie Cooper</h4>
                <p class="mb-4 text-[#666]">apple inc ltd</p>
                <p>
                  Our platform helps build secure onboarding authentica
                  experiences & engage your users. We build .
                </p>
                <div
                  class="review-rating mt-6 flex items-center justify-center space-x-2.5"
                >
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star.svg" alt="" />
                  <img src="images/icons/star-white.svg" alt="" />
                </div>
              </div>
            </div>
          </div>
          <!-- If we need pagination -->
          <div
            class="swiper-pagination reviews-carousel-pagination !bottom-0"
          ></div>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- Reviews -->


<%@include file="/WEB-INF/views/inc/footer.jsp" %>
	<script>

	</script>
</body>
</html>