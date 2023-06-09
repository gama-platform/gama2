/*******************************************************************************************************
 *
 * ArgumentDefinition.java, in gaml.compiler, is part of the source code of the
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
 * A representation of the model object '<em><b>Argument Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gaml.compiler.gaml.ArgumentDefinition#getType <em>Type</em>}</li>
 *   <li>{@link gaml.compiler.gaml.ArgumentDefinition#getDefault <em>Default</em>}</li>
 * </ul>
 *
 * @see gaml.compiler.gaml.GamlPackage#getArgumentDefinition()
 * @model
 * @generated
 */
public interface ArgumentDefinition extends VarDefinition
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(Expression)
   * @see gaml.compiler.gaml.GamlPackage#getArgumentDefinition_Type()
   * @model containment="true"
   * @generated
   */
  Expression getType();

  /**
   * Sets the value of the '{@link gaml.compiler.gaml.ArgumentDefinition#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(Expression value);

  /**
   * Returns the value of the '<em><b>Default</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default</em>' containment reference.
   * @see #setDefault(Expression)
   * @see gaml.compiler.gaml.GamlPackage#getArgumentDefinition_Default()
   * @model containment="true"
   * @generated
   */
  Expression getDefault();

  /**
   * Sets the value of the '{@link gaml.compiler.gaml.ArgumentDefinition#getDefault <em>Default</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Default</em>' containment reference.
   * @see #getDefault()
   * @generated
   */
  void setDefault(Expression value);

} // ArgumentDefinition
