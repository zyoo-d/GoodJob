package com.good.web;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.good.admin.visitor.IpTracker;

/**
 * 서버 시작 시 실행되는 리스너 클래스입니다.
 * IP 주소 정리 작업을 매일 자정에 실행하도록 스케줄링합니다.
 */
@WebListener
public class ServerStartupListener implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    /**
     * 서버 시작 시 호출되는 메서드입니다.
     * IP 주소 정리 작업을 매일 자정에 실행하도록 스케줄링합니다.
     *
     * @param event ServletContextEvent 객체
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();

        // 다음 자정 시간 계산
        LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0);
        Date midnightDate = Date.from(midnight.atZone(ZoneId.systemDefault()).toInstant());

        // 자정에 실행되도록 스케줄링
        scheduler.scheduleAtFixedRate(IpTracker::cleanUpIpAddresses, midnightDate.getTime() - System.currentTimeMillis(), TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
    }

}