import parse from 'html-react-parser';
import { css, cx } from 'styled-system/css';

const styles = {
  title: css({
    fontSize: '1.25rem',
    fontWeight: 'bold',
    padding: '0.25em',
    margin: '1.5rem auto 0',
  }),
  content: css({
    padding: '0.25rem 0.5rem',
  }),
  roleBox: css({
    display: 'grid',
    gridTemplateColumns: '1fr',
    gap: '0.5rem 1rem',
    alignItems: 'center',
    maxWidth: '640px',
    margin: '2rem auto',

    sm: {
      gridTemplateColumns: 'minmax(120px, 128px) 1fr',
    },
  }),
  roleFigure: css({
    display: 'grid',
    justifyItems: 'center',
    sm: {
      gridColumn: '1/2',
    },
  }),
  roleImage: css({
    maxWidth: '128px',
  }),
  roleFigCaption: css({}),
  roleDescription: css({
    sm: {
      gridColumn: '2/3',
    },
  }),
};
type Role = {
  imgSrc: string;
  altText: string;
  caption: string;
  description: string;
};

type Props = {
  roleTitle: string;
  backgroundColor: string;
  description: string;
  roles: Role[];
};
export const RoleExplanation: React.FC<Props> = ({
  roleTitle,
  backgroundColor,
  description,
  roles,
}) => {
  const customStyle = css({ backgroundColor });

  return (
    <section>
      <h3 className={cx(styles.title, customStyle)}>{roleTitle}</h3>
      <div className={styles.content}>
        {parse(description)}
        <ul>
          {roles.map((role, index) => {
            return (
              <li key={index} className={styles.roleBox}>
                <figure className={styles.roleFigure}>
                  <img
                    src={role.imgSrc}
                    alt={role.altText}
                    className={styles.roleImage}
                  />
                  <figcaption className={styles.roleFigCaption}>
                    {role.caption}
                  </figcaption>
                </figure>
                <p className={styles.roleDescription}>{role.description}</p>
              </li>
            );
          })}
        </ul>
      </div>
    </section>
  );
};
