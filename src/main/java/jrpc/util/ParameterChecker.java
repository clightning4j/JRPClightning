package jrpc.util;

import jrpc.clightning.exceptions.CLightningException;

public class ParameterChecker {

  public static void doCheckString(String command, String name, String value, boolean onlyNull) {
    if (value == null || (value.isEmpty() && !onlyNull)) {
      String message = "Propriety " + name + " in the command " + command;
      if (value == null) {
        message += " null";
      } else if (value.isEmpty() && !onlyNull) {
        message += " empty";
      }
      throw new CLightningException(message);
    }
  }

  public static void doCheckObjectNotNull(String command, String name, Object value) {
    if (value == null) {
      String message = String.format("Propriety %s in command %s null", name, command);
      throw new CLightningException(message);
    }
  }

  public static void doCheckPositiveNumber(String command, String name, Number value) {
    doCheckPositiveNumber(command, name, value, false);
  }

  public static void doCheckPositiveNumber(
      String command, String name, Number value, boolean admitNull) {
    // This use case mean that the default value depend from the blockchain status
    // is difficult estimate a value number if it depend from the blockchain status
    if (value == null) return;
    if (value.floatValue() < 0) {
      String message = "Propriety " + name + " in the command " + command + " must be positive";
      throw new CLightningException(message);
    }
  }
}
