/*******************************************************************************************************
 *
 * UnitName.java, in gaml.compiler, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gaml.compiler.gaml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unit Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gaml.compiler.gaml.UnitName#getRef <em>Ref</em>}</li>
 * </ul>
 *
 * @see gaml.compiler.gaml.GamlPackage#getUnitName()
 * @model
 * @generated
 */
public interface UnitName extends Expression
{
  /**
   * Returns the value of the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref</em>' reference.
   * @see #setRef(UnitFakeDefinition)
   * @see gaml.compiler.gaml.GamlPackage#getUnitName_Ref()
   * @model
   * @generated
   */
  UnitFakeDefinition getRef();

  /**
   * Sets the value of the '{@link gaml.compiler.gaml.UnitName#getRef <em>Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ref</em>' reference.
   * @see #getRef()
   * @generated
   */
  void setRef(UnitFakeDefinition value);

} // UnitName
