package com.videotest;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;

public class NotificationService {

	// 타임아웃 설정
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

	// 알림 저장
	private Map<Long, SseEmitter> SseEmitterStorage = new HashMap<>();


	//SSE Emitter를 생성하는 메소드
	private SseEmitter createEmitter(Long id) {
		SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);

		//생성된 SSE Emitter를 저장소에 저장
		SseEmitterStorage.put(id, emitter);

		// Emitter가 완료될 때(모든 데이터가 성공적으로 전송된 상태) Emitter를 삭제한다.
		emitter.onCompletion(() -> SseEmitterStorage.remove(id));

		// Emitter가 타임아웃 되었을 때(지정된 시간동안 어떠한 이벤트도 전송되지 않았을 때) Emitter를 삭제한다.
		emitter.onTimeout(() -> SseEmitterStorage.remove(id));

		return emitter;
	}


}
