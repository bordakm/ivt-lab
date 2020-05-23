package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockPrimaryStore;
  private TorpedoStore mockSecondaryStore;

  @BeforeEach
  public void init(){
    mockPrimaryStore = mock(TorpedoStore.class);
    mockSecondaryStore = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPrimaryStore, mockSecondaryStore);  
  }

  @Test
  public void fireTorpedo_Single_Success(){
    when(mockPrimaryStore.fire(1)).thenReturn(true);
    when(mockSecondaryStore.fire(1)).thenReturn(true);
    when(mockPrimaryStore.isEmpty()).thenReturn(false);
    when(mockSecondaryStore.isEmpty()).thenReturn(false);


    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockPrimaryStore, times(1)).fire(1);
    verify(mockSecondaryStore, times(0)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Twice_Success(){
    when(mockPrimaryStore.fire(1)).thenReturn(true);
    when(mockSecondaryStore.fire(1)).thenReturn(true);
    when(mockPrimaryStore.isEmpty()).thenReturn(false);
    when(mockSecondaryStore.isEmpty()).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockPrimaryStore, times(1)).fire(1);
    verify(mockSecondaryStore, times(1)).fire(1);
    assertEquals(true, result);
    assertEquals(true, result2);
  }

  @Test
  public void fireTorpedo_Single_FirstStoreEmpty_Success(){
    when(mockPrimaryStore.fire(1)).thenReturn(false);
    when(mockSecondaryStore.fire(1)).thenReturn(true);
    when(mockPrimaryStore.isEmpty()).thenReturn(true);
    when(mockSecondaryStore.isEmpty()).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockPrimaryStore, times(0)).fire(1);
    verify(mockSecondaryStore, times(1)).fire(1);
    assertEquals(true, result);
  }


  @Test
  public void fireTorpedo_Single_Twice_SecondStoreEmpty_Success(){
    when(mockPrimaryStore.fire(1)).thenReturn(true);
    when(mockSecondaryStore.fire(1)).thenReturn(false);
    when(mockPrimaryStore.isEmpty()).thenReturn(false);
    when(mockSecondaryStore.isEmpty()).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockPrimaryStore, times(2)).fire(1);
    verify(mockSecondaryStore, times(0)).fire(1);
    assertEquals(true, result);
    assertEquals(true, result2);
  }


  @Test
  public void fireTorpedo_All_Success(){
    when(mockPrimaryStore.fire(1)).thenReturn(true);
    when(mockSecondaryStore.fire(1)).thenReturn(true);
    when(mockPrimaryStore.isEmpty()).thenReturn(false);
    when(mockSecondaryStore.isEmpty()).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

    verify(mockPrimaryStore, times(1)).fire(1);
    verify(mockSecondaryStore, times(1)).fire(1);
    assertEquals(true, result);
  }

}
