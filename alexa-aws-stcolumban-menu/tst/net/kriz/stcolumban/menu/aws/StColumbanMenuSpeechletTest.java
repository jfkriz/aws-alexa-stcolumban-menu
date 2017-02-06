package net.kriz.stcolumban.menu.aws;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.Context;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;

public class StColumbanMenuSpeechletTest {
	private StColumbanMenuSpeechlet fixture;

	@Before
	public void setUp() throws Exception {
		fixture = new StColumbanMenuSpeechlet();
	}

	@Test
	public void testOnLaunch() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnIntentGetMenu() {
		Context context = Context.builder().build();
		Map<String, Slot> slots = new HashMap<String, Slot>();
		slots.put("MenuDate", Slot.builder().withName("MenuDate").withValue("2/14/2017").build());
		Intent intent = Intent.builder().withName("GetMenuIntent").withSlots(slots).build();
		IntentRequest request = IntentRequest.builder().withIntent(intent).withRequestId("12345").build();
		
		SpeechletRequestEnvelope<IntentRequest> requestEnvelope = SpeechletRequestEnvelope.<IntentRequest>builder().withContext(context).withRequest(request).build();
		SpeechletResponse response = fixture.onIntent(requestEnvelope);
		assertNotNull(response);
		assertNotNull(response.getOutputSpeech());
	}

}
