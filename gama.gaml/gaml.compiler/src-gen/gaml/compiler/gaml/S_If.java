/*******************************************************************************************************
 *
 * S_If.java, in gaml.compiler, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gaml.compiler.gaml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SIf</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gaml.compiler.gaml.S_If#getElse <em>Else</em>}</li>
 * </ul>
 *
 * @see gaml.compiler.gaml.GamlPackage#getS_If()
 * @model
 * @generated
 */
public interface S_If extends Statement
{
  /**
   * Returns the value of the '<em><b>Else</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else</em>' containment reference.
   * @see #setElse(EObject)
   * @see gaml.compiler.gaml.GamlPackage#getS_If_Else()
   * @model containment="true"
   * @generated
   */
  EObject getElse();

  /**
   * Sets the value of the '{@link gaml.compiler.gaml.S_If#getElse <em>Else</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Else</em>' containment reference.
   * @see #getElse()
   * @generated
   */
  void setElse(EObject value);

} // S_If
