package main.wonprice.domain.member.controller;

import lombok.AllArgsConstructor;
import main.wonprice.domain.member.dto.NotificationResponseDto;
import main.wonprice.domain.member.entity.Notification;
import main.wonprice.domain.member.mapper.NotificationMapper;
import main.wonprice.domain.member.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper mapper;

//    안읽은 알림 개수
    @GetMapping("/count")
    public ResponseEntity getNotificationCount() {

        Long count = notificationService.getUnreadCount();

        Map<String, Long> response = new HashMap<>();
        response.put("unreadNotification", count);

        return new ResponseEntity(response, HttpStatus.OK);
    }

//    알림 목록
    @GetMapping
    public ResponseEntity getNotifications(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Notification> notifications = notificationService.findNotifications(pageable);
        Page<NotificationResponseDto> response = notifications.map(mapper::notificationToResponseDto);

        return new ResponseEntity(response, HttpStatus.OK);
    }

//    알림 단일 읽음 표시
    @PatchMapping("/{notification-id}")
    public ResponseEntity setRead(@PathVariable("notification-id") Long notificationId) {

        notificationService.setReadTrue(notificationId);

        return ResponseEntity.ok("🌟🌟🌟 Success 🌟🌟🌟");
    }

    //    모든 알림 읽음 표시
    @PatchMapping("/all")
    public ResponseEntity setReads() {

        notificationService.setReadsTrue();

        return ResponseEntity.ok("🌟🌟🌟 Success 🌟🌟🌟");
    }

//    읽은 알림 삭제
    @DeleteMapping("/delete")
    public ResponseEntity deleteNotifications() {

        notificationService.deleteNotifications();

        return ResponseEntity.ok("🌟🌟🌟 Success 🌟🌟🌟");
    }
}
