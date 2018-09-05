package com.github.pwittchen.neurosky.library;

import android.os.Message;
import com.github.pwittchen.neurosky.library.listener.DeviceMessageListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class DeviceMessageHandlerTest {

  @Mock
  DeviceMessageListener deviceMessageListener;

  @Mock
  Message message;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldCreateHandler() {
    // when
    DeviceMessageHandler handler = new DeviceMessageHandler(deviceMessageListener);

    // then
    assertThat(handler).isNotNull();
  }

  @Test
  public void shouldHandleMessage() {
    // given
    DeviceMessageHandler handler = new DeviceMessageHandler(deviceMessageListener);

    // when
    handler.handleMessage(message);

    // then
    verify(deviceMessageListener).onMessageReceived(message);
  }


  @Test
  public void shouldNotHandleMessage() {
    // given
    DeviceMessageHandler handler = new DeviceMessageHandler(null);

    // when
    handler.handleMessage(message);

    // then
    verify(deviceMessageListener, times(0)).onMessageReceived(message);
  }
}