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
    this.ship = new GT4500(mockPrimaryStore,mockSecondaryStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockPrimaryStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockPrimaryStore, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockPrimaryStore.fire(1)).thenReturn(true);
    when(mockSecondaryStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockPrimaryStore, times(1)).fire(1);
    verify(mockSecondaryStore, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_SINGLE_Failure() {
    //Arrange
    when(mockPrimaryStore.isEmpty()).thenReturn(true);
    when(mockSecondaryStore.isEmpty()).thenReturn(true);

    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);


    //Assert

    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_SecondTorpedoSucces() {
    //Arrange
    when(mockPrimaryStore.isEmpty()).thenReturn(true);
    when(mockSecondaryStore.fire(1)).thenReturn(true);


    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);


    //Assert

    verify(mockSecondaryStore, times(1)).fire(1);
    assertEquals(false, result);
  }
  
  @Test
  public void fireTorpedo_All_FirstTorpedoSucces() {
    //Arrange
    when(mockPrimaryStore.fire(1)).thenReturn(true);
    when(mockSecondaryStore.isEmpty()).thenReturn(true);


    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);


    //Assert

    verify(mockPrimaryStore, times(1)).fire(1);
    assertEquals(false, result);
  }
  
  @Test
  public void fireTorpedo_ALL_Failure() {
    //Arrange
    when(mockPrimaryStore.fire(1)).thenReturn(false);
    when(mockSecondaryStore.fire(1)).thenReturn(false);


    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);


    //Assert

    verify(mockPrimaryStore, times(1)).fire(1);
    verify(mockSecondaryStore, times(1)).fire(1);
    assertEquals(false, result);
  }


  @Test
  public void fireTorpedo_SINGLE_StartWithSecondTorpedo() {
    //Arrange
    
   
    when(mockPrimaryStore.isEmpty()).thenReturn(true);
    when(mockSecondaryStore.isEmpty()).thenReturn(false);
    when(mockSecondaryStore.fire(1)).thenReturn(true);

    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);


    //Assert

    verify(mockSecondaryStore, times(1)).fire(1);
    assertEquals(true, result);
  }
  
  @Test
  public void fireTorpedo_SINGLE_StartWithSecondTorpedo2() {
    //Arrange
    
    when(mockPrimaryStore.isEmpty()).thenReturn(true);
    when(mockSecondaryStore.isEmpty()).thenReturn(false);
    when(mockSecondaryStore.fire(1)).thenReturn(true);

    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);


    //Assert

    verify(mockSecondaryStore, times(1)).fire(1);
    assertEquals(true, result);
  }
}
